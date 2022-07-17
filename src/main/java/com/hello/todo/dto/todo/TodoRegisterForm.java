package com.hello.todo.dto.todo;

import lombok.Data;
import lombok.Getter;

@Getter
public class TodoRegisterForm {
    private String name;
    private boolean completed;
}
