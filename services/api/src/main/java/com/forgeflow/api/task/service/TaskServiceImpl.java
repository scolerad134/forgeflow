package com.forgeflow.api.task.service;

import com.forgeflow.api.task.domain.Task;
import com.forgeflow.api.task.domain.TaskStatus;
import com.forgeflow.api.task.domain.TaskType;
import com.forgeflow.api.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Override
    public UUID createTask(TaskType type, String payload) {
        Task createdTask = Task.builder()
                .id(UUID.randomUUID())
                .type(type)
                .status(TaskStatus.PENDING)
                .payload(payload)
                .created_at(Instant.now())
                .build();

        taskRepository.save(createdTask);

        return createdTask.getId();
    }
}
