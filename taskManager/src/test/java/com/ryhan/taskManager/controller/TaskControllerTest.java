package com.ryhan.taskManager.controller;

import com.ryhan.taskManager.controller.TaskController;
import com.ryhan.taskManager.entity.Task;
import com.ryhan.taskManager.enums.TaskStatus;
import com.ryhan.taskManager.exceptions.InvalidDateException;
import com.ryhan.taskManager.exceptions.InvalidTaskException;
import com.ryhan.taskManager.exceptions.TaskNotFoundException;
import com.ryhan.taskManager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTask_ValidTask() {
        Task task = new Task();
        task.setTitle("Task Title");
        when(taskService.createTask(any(Task.class))).thenReturn(task);

        ResponseEntity<Task> response = taskController.createTask(task);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(task, response.getBody());
    }

    @Test
    public void testCreateTask_InvalidTask() {
        Task task = new Task();
        task.setTitle(null); // Simulate invalid task

        when(taskService.createTask(any(Task.class))).thenThrow(new InvalidTaskException("Invalid task"));

        ResponseEntity<Task> response = taskController.createTask(task);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetAllTasks() {
        List<Task> tasks = new ArrayList<>();
        when(taskService.getAllTasks()).thenReturn(tasks);

        ResponseEntity<List<Task>> response = taskController.getAllTasks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }

    @Test
    public void testGetTaskById_ExistingTask() {
        Task task = new Task();
        when(taskService.getTaskById(anyLong())).thenReturn(task);

        ResponseEntity<Task> response = taskController.getTaskById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, response.getBody());
    }

    @Test
    public void testGetTaskById_TaskNotFound() {
        when(taskService.getTaskById(anyLong())).thenThrow(new TaskNotFoundException("Task not found"));

        ResponseEntity<Task> response = taskController.getTaskById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateTask_ValidTask() {
        Task updatedTask = new Task();
        updatedTask.setId(1L);
        when(taskService.updateTask(anyLong(), any(Task.class))).thenReturn(updatedTask);

        ResponseEntity<Task> response = taskController.updateTask(1L, updatedTask);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTask, response.getBody());
    }


    @Test
    public void testUpdateTask_InvalidTask() {
        Task updatedTask = new Task();
        updatedTask.setId(1L);
        when(taskService.updateTask(anyLong(), any(Task.class)))
                .thenThrow(new InvalidTaskException("Invalid task"));

        ResponseEntity<Task> response = taskController.updateTask(1L, updatedTask);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }
    

    @Test
    public void testUpdateTask_InvalidDate() {
        Task updatedTask = new Task();
        updatedTask.setId(1L);
        when(taskService.updateTask(anyLong(), any(Task.class)))
                .thenThrow(new InvalidDateException("Invalid date"));

        ResponseEntity<Task> response = taskController.updateTask(1L, updatedTask);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }
    
    @Test
    public void testDeleteTask_ExistingTask() {
        doNothing().when(taskService).deleteTask(anyLong());

        ResponseEntity<Void> response = taskController.deleteTask(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteTask_TaskNotFound() {
        doThrow(new TaskNotFoundException("Task not found")).when(taskService).deleteTask(anyLong());

        ResponseEntity<Void> response = taskController.deleteTask(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

 
    @Test
    public void testGetTasksByStatus_ValidStatus() {
        Page<Task> tasks = mock(Page.class);
        when(taskService.getTasksByStatus(any(TaskStatus.class), anyInt(), anyInt())).thenReturn(tasks);

        ResponseEntity<Page<Task>> response = taskController.getTasksByStatus(TaskStatus.IN_PROGRESS, 0, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }

    @Test
    public void testGetTasksByStatus_InvalidStatus() {
        when(taskService.getTasksByStatus(any(TaskStatus.class), anyInt(), anyInt()))
                .thenThrow(new IllegalArgumentException("Invalid status"));

        ResponseEntity<Page<Task>> response = taskController.getTasksByStatus(null, 0, 10);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
  

    @Test
    public void testGetTasksByDueDateRange_ValidRange() {
        List<Task> tasks = new ArrayList<>();
        when(taskService.getTasksByDueDateRange(any(LocalDate.class), any(LocalDate.class))).thenReturn(tasks);

        ResponseEntity<List<Task>> response = taskController.getTasksByDueDateRange(LocalDate.now(), LocalDate.now().plusDays(7));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }

    @Test
    public void testGetTasksByDueDateRange_InvalidRange() {
        when(taskService.getTasksByDueDateRange(any(LocalDate.class), any(LocalDate.class)))
                .thenThrow(new InvalidDateException("Invalid date range"));

        ResponseEntity<List<Task>> response = taskController.getTasksByDueDateRange(LocalDate.now(), LocalDate.now().minusDays(7));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }
}
