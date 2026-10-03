package com.forgeflow.api.task.repository;

import com.forgeflow.api.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface TaskRepository extends JpaRepository<Task, UUID> {
}
