package com.ryhan.taskManager.repository;

import com.ryhan.taskManager.entity.Task;
import com.ryhan.taskManager.enums.TaskStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskRepositoryTest {

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testFindByStatus() {
        TaskStatus status = TaskStatus.IN_PROGRESS;
        Task task1 = new Task("Task 1", "Description 1", LocalDate.now(), status);
        Task task2 = new Task("Task 2", "Description 2", LocalDate.now(), status);
        List<Task> tasks = Arrays.asList(task1, task2);
        Page<Task> taskPage = mock(Page.class);
        when(taskPage.getContent()).thenReturn(tasks);
        when(taskRepository.findByStatus(status, Pageable.unpaged())).thenReturn(taskPage);

        Page<Task> result = taskRepository.findByStatus(status, Pageable.unpaged());
        assertEquals(tasks.size(), result.getContent().size());
        assertEquals(task1.getTitle(), result.getContent().get(0).getTitle());
        assertEquals(task2.getTitle(), result.getContent().get(1).getTitle());
    }

    @Test
    public void testFindByDueDateBetween() {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 1, 31);
        Task task1 = new Task("Task 1", "Description 1", LocalDate.of(2022, 1, 15), TaskStatus.PENDING);
        Task task2 = new Task("Task 2", "Description 2", LocalDate.of(2022, 1, 20), TaskStatus.IN_PROGRESS);
        List<Task> tasks = Arrays.asList(task1, task2);
        when(taskRepository.findByDueDateBetween(startDate, endDate)).thenReturn(tasks);

        List<Task> result = taskRepository.findByDueDateBetween(startDate, endDate);
        assertEquals(tasks.size(), result.size());
        assertEquals(task1.getTitle(), result.get(0).getTitle());
        assertEquals(task2.getTitle(), result.get(1).getTitle());
    }
}
