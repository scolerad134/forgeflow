package com.forgeflow.api.task.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TaskType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TaskStatus status;

    @Column(columnDefinition = "jsonb")
    private String payload;

    @Column(columnDefinition = "jsonb")
    private String result;

    @Column(nullable = false)
    private Instant created_at;

    private Instant started_at;

    private Instant finished_at;
}
