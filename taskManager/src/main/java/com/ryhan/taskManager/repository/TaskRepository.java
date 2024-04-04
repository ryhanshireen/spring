package com.ryhan.taskManager.repository;

import com.ryhan.taskManager.entity.Task;
import com.ryhan.taskManager.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByStatus(TaskStatus status, Pageable pageable);

    List<Task> findByDueDateBetween(LocalDate startDate, LocalDate endDate);
}
