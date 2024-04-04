package com.ryhan.taskManager.controller;

import com.ryhan.taskManager.entity.Task;
import com.ryhan.taskManager.enums.TaskStatus;
import com.ryhan.taskManager.exceptions.InvalidDateException;
import com.ryhan.taskManager.exceptions.InvalidTaskException;
import com.ryhan.taskManager.exceptions.TaskNotFoundException;
import com.ryhan.taskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        try {
            Task createdTask = taskService.createTask(task);
            return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
        } catch (InvalidTaskException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
       
       }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        try {
            Task task = taskService.getTaskById(taskId);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (TaskNotFoundException e) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);   
        }
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        try {
            Task task = taskService.updateTask(taskId, updatedTask);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (InvalidTaskException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (InvalidDateException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        try {
            taskService.deleteTask(taskId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (TaskNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<Page<Task>> getTasksByStatus(
            @PathVariable TaskStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {try {
        Page<Task> tasks = taskService.getTasksByStatus(status, page, size);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	
    }
    }

    @GetMapping("/due-date-range")
    public ResponseEntity<List<Task>> getTasksByDueDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) { try {
        List<Task> tasks = taskService.getTasksByDueDateRange(startDate, endDate);
       
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }catch (InvalidDateException e) {
    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    }
}