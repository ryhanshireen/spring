package com.ryhan.taskManager.service;

import com.ryhan.taskManager.entity.Task;
import com.ryhan.taskManager.enums.TaskStatus;
import com.ryhan.taskManager.exceptions.InvalidDateException;
import com.ryhan.taskManager.exceptions.InvalidTaskException;
import com.ryhan.taskManager.exceptions.TaskNotFoundException;
import com.ryhan.taskManager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTasksByStatus() {
        Page<Task> page = mock(Page.class);
        when(taskRepository.findByStatus(any(TaskStatus.class), any(Pageable.class))).thenReturn(page);
        Page<Task> result = taskService.getTasksByStatus(TaskStatus.IN_PROGRESS, 0, 10);
        assertNotNull(result);
        assertEquals(page, result);
        verify(taskRepository, times(1)).findByStatus(TaskStatus.IN_PROGRESS, PageRequest.of(0, 10));
    }

    @Test
    public void testGetTasksByDueDateRange() {
        List<Task> tasks = new ArrayList<>();
        when(taskRepository.findByDueDateBetween(any(LocalDate.class), any(LocalDate.class))).thenReturn(tasks);
        List<Task> result = taskService.getTasksByDueDateRange(LocalDate.now(), LocalDate.now().plusDays(7));
        assertNotNull(result);
        assertEquals(tasks, result);
        verify(taskRepository, times(1)).findByDueDateBetween(LocalDate.now(), LocalDate.now().plusDays(7));
    }

    @Test
    public void testCreateTask() {
        Task task = new Task();
        task.setTitle("Valid Task Title"); 
        task.setDueDate(LocalDate.now().plusDays(1)); 
        task.setStatus(TaskStatus.COMPLETED);
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        assertDoesNotThrow(() -> taskService.createTask(task));
    }


    @Test
    public void testGetAllTasks() {
        List<Task> tasks = new ArrayList<>();
        when(taskRepository.findAll()).thenReturn(tasks);
        List<Task> result = taskService.getAllTasks();
        assertNotNull(result);
        assertEquals(tasks, result);
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    public void testGetTaskById() {
        Task task = new Task();
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));
        Task result = taskService.getTaskById(1L);
        assertNotNull(result);
        assertEquals(task, result);
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateTask() {
        Task existingTask = new Task();
        existingTask.setId(1L);
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(existingTask);
        Task updatedTask = new Task();
        updatedTask.setId(1L);
        updatedTask.setTitle("Updated Title");
        updatedTask.setDescription("Updated Description");
        updatedTask.setDueDate(LocalDate.now().plusDays(1));
        updatedTask.setStatus(TaskStatus.CANCELLED); 
        Task result = taskService.updateTask(1L, updatedTask);
        assertNotNull(result);
        assertEquals(updatedTask.getTitle(), result.getTitle());
        assertEquals(updatedTask.getDescription(), result.getDescription());
        assertEquals(updatedTask.getDueDate(), result.getDueDate());
        assertEquals(updatedTask.getStatus(), result.getStatus());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(existingTask);
    }

    @Test
    public void testDeleteTask() {
    	Task existingTask = new Task();
        existingTask.setId(1L);
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(existingTask));
        assertDoesNotThrow(() -> taskService.deleteTask(1L));
        verify(taskRepository, times(1)).delete(existingTask);
    }

    @Test
    public void testCreateTask_ValidTask() {
        Task task = new Task();
        task.setTitle("Task Title");
        task.setDueDate(LocalDate.now().plusDays(1));
        task.setStatus(TaskStatus.COMPLETED);
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        Task result = taskService.createTask(task);
        assertNotNull(result);
        assertEquals(task, result);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testCreateTask_InvalidTask_TitleEmpty() {
        Task task = new Task(); 
        assertThrows(InvalidTaskException.class, () -> taskService.createTask(task));
    }
    @Test
    public void testCreateTask_InvalidTask_DueDateInPast() {
        // Mock Task object
        Task task = mock(Task.class);
        
        // Stub behavior for setDueDate method
        doNothing().when(task).setDueDate(any(LocalDate.class));
        
        // Set up task properties
        when(task.getTitle()).thenReturn("Task Title");
        when(task.getDescription()).thenReturn("Task Description");
        when(task.getStatus()).thenReturn(TaskStatus.COMPLETED); // Valid status

        // Set a past due date
        LocalDate pastDueDate = LocalDate.now().minusDays(1);
        when(task.getDueDate()).thenReturn(pastDueDate);

        // Verify that InvalidDateException is thrown
        assertThrows(InvalidDateException.class, () -> taskService.createTask(task));
    }
    @Test
    public void testCreateTask_InvalidTask_InvalidStatus() {
        Task task = new Task();
        task.setTitle("Task Title");
        task.setDueDate(LocalDate.now().plusDays(1));
        task.setStatus(null); 
        assertThrows(InvalidTaskException.class, () -> taskService.createTask(task));
    }

}
