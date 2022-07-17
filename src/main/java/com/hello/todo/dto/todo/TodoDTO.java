package com.hello.todo.dto.todo;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class TodoDTO {
    private Long id;

    private String name;

    private boolean completed;

    private LocalDateTime completedAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
