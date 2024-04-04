package com.ryhan.taskManager.service;

import com.ryhan.taskManager.entity.Task;
import com.ryhan.taskManager.enums.TaskStatus;
import com.ryhan.taskManager.exceptions.InvalidDateException;
import com.ryhan.taskManager.exceptions.InvalidTaskException;
import com.ryhan.taskManager.exceptions.TaskNotFoundException;
import com.ryhan.taskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Page<Task> getTasksByStatus(TaskStatus status, int page, int size) {
    	 if (status == null) {
             throw new IllegalArgumentException("Status cannot be null");
         }
        Pageable pageable = PageRequest.of(page, size);
        return taskRepository.findByStatus(status, pageable);
    }

    public List<Task> getTasksByDueDateRange(LocalDate startDate, LocalDate endDate) {
    	 if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
             throw new InvalidDateException("Invalid date range");}
        return taskRepository.findByDueDateBetween(startDate, endDate);
    }

    public Task createTask(Task task) {
        validateTask(task);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + taskId));
    }

    public Task updateTask(Long taskId, Task updatedTask) {
        validateTask(updatedTask);
        Task existingTask = getTaskById(taskId);
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setStatus(updatedTask.getStatus());
        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long taskId) {
        Task existingTask = getTaskById(taskId);
        taskRepository.delete(existingTask);
    }

    void validateTask(Task task) {
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new InvalidTaskException("Task title cannot be empty");
        }
        if (task.getDueDate() != null && task.getDueDate().isBefore(LocalDate.now())) {
            throw new InvalidDateException("Due date must be in the future");
        }
//        TaskStatus[] validStatuses = TaskStatus.values();
//        System.out.println(validStatuses);
//        boolean isValidStatus = false;
//        for (TaskStatus status : validStatuses) {
//            if (status.equals(task.getStatus())) {
//                isValidStatus = true;
//                break;
//            }
//        }
        boolean isValidStatus = false;
        if(task.getStatus()==TaskStatus.PENDING
        		||task.getStatus()==TaskStatus.IN_PROGRESS
        		||task.getStatus()==TaskStatus.COMPLETED
        		||task.getStatus()==TaskStatus.CANCELLED) {
        	isValidStatus=true;
        }
        if (!isValidStatus) {
            throw new InvalidTaskException("Invalid task status");
        }
    }
}
