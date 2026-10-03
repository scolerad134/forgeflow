package com.forgeflow.api.task.service;


import com.forgeflow.api.task.domain.TaskType;

import java.util.UUID;

public interface TaskService {

    UUID createTask(TaskType type, String payload);
}
