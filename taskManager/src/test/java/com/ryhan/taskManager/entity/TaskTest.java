package com.ryhan.taskManager.entity;

import com.ryhan.taskManager.enums.TaskStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

	@Test
	public void testSetDueDate() {
	    Task task = new Task();
	    LocalDate futureDate = LocalDate.now().plusDays(1);
	    task.setDueDate(futureDate);
	    assertEquals(futureDate, task.getDueDate());
	    LocalDate pastDate = LocalDate.now().minusDays(1);
	  
	}

    @Test
    public void testTaskConstructor() {
        String title = "Task Title";
        String description = "Task Description";
        LocalDate dueDate = LocalDate.now().plusDays(1);
        TaskStatus status = TaskStatus.IN_PROGRESS;

        Task task = new Task(title, description, dueDate, status);

        assertEquals(title, task.getTitle());
        assertEquals(description, task.getDescription());
        assertEquals(dueDate, task.getDueDate());
        assertEquals(status, task.getStatus());
    }

    @Test
    public void testTaskSetterGetter() {
        Task task = new Task();
        String title = "Task Title";
        String description = "Task Description";
        LocalDate dueDate = LocalDate.now().plusDays(1);
        TaskStatus status = TaskStatus.IN_PROGRESS;

        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setStatus(status);
        assertEquals(title, task.getTitle());
        assertEquals(description, task.getDescription());
        assertEquals(dueDate, task.getDueDate());
        assertEquals(status, task.getStatus());
    }

    
}