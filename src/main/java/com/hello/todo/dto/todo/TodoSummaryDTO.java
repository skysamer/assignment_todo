package com.hello.todo.dto.todo;

import lombok.Getter;

@Getter
public class TodoSummaryDTO {
    private Long id;

    private String name;

    private boolean completed;
}
