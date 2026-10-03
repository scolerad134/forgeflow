package com.forgeflow.api.task.service;


import com.forgeflow.api.task.domain.Task;
import com.forgeflow.api.task.domain.TaskStatus;
import com.forgeflow.api.task.domain.TaskType;
import com.forgeflow.api.task.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskServiceImpl taskService;

    @Test
    void shouldCreatePendingTask() {
        String payload = """
                {
                    "method": "GET",
                    "url": "https://example.com"
                }
                """;

        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);

        when(taskRepository.save(taskCaptor.capture()))
                .then(invocation -> invocation.getArgument(0));

        UUID taskId = taskService.createTask(
                TaskType.HTTP_REQUEST,
                payload
        );

        Task taskSaved = taskCaptor.getValue();

        assertNotNull(taskId);
        assertEquals(taskId, taskSaved.getId());
        assertEquals(TaskType.HTTP_REQUEST, taskSaved.getType());
        assertEquals(TaskStatus.PENDING, taskSaved.getStatus());
        assertEquals(payload, taskSaved.getPayload());
        assertNotNull(taskSaved.getCreated_at());
        assertNull(taskSaved.getStarted_at());
        assertNull(taskSaved.getFinished_at());

        verify(taskRepository).save(taskSaved);
    }
}
