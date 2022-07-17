package com.hello.todo.controller;

import com.hello.todo.config.JwtTokenProvider;
import com.hello.todo.dto.todo.TodoDTO;
import com.hello.todo.dto.todo.TodoRegisterForm;
import com.hello.todo.dto.todo.TodoSummaryDTO;
import com.hello.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public TodoDTO register(@RequestBody TodoRegisterForm todoRegisterForm, HttpServletRequest request){
        String token = request.getHeader("apiKeys");
        String username = jwtTokenProvider.getUserPk(token);

        return todoService.register(todoRegisterForm, username);
    }

    @GetMapping("/{todoId}")
    public TodoDTO get(@PathVariable Long todoId){
        return todoService.get(todoId);
    }

    @GetMapping
    public List<TodoSummaryDTO> getList(){
        return todoService.getList();
    }

    @PutMapping("/{todoId}")
    public TodoDTO modify(@PathVariable Long todoId, @RequestBody TodoRegisterForm todoRegisterForm){
        return todoService.modify(todoId, todoRegisterForm);
    }

    @DeleteMapping("/{todoId}")
    public void remove(@PathVariable Long todoId){
        todoService.remove(todoId);
    }
}
