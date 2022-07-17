package com.hello.todo.service;

import com.hello.todo.domain.Todo;
import com.hello.todo.domain.User;
import com.hello.todo.dto.todo.TodoDTO;
import com.hello.todo.dto.todo.TodoRegisterForm;
import com.hello.todo.dto.todo.TodoSummaryDTO;
import com.hello.todo.repository.TodoRepository;
import com.hello.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public TodoDTO register(TodoRegisterForm todoRegisterForm, String username){
        Todo newTodo = modelMapper.map(todoRegisterForm, Todo.class);
        User user = userRepository.findByName(username);

        newTodo.setUser(user);
        newTodo.completeTodo(checkIsCompleted(todoRegisterForm));

        Todo registeredTodo = todoRepository.save(newTodo);
        return modelMapper.map(registeredTodo, TodoDTO.class);
    }

    private LocalDateTime checkIsCompleted(TodoRegisterForm todoRegisterForm){
        return todoRegisterForm.isCompleted() ? LocalDateTime.now() : null;
    }

    public TodoDTO get(Long todoId){
        Todo todo = todoRepository.findById(todoId).orElse(null);
        return modelMapper.map(todo, TodoDTO.class);
    }

    public List<TodoSummaryDTO> getList(){
        List<Todo> todoList = todoRepository.findAll();
        return List.of(modelMapper.map(todoList, TodoSummaryDTO[].class));
    }

    public TodoDTO modify(Long todoId, TodoRegisterForm todoRegisterForm){
        Todo todo = todoRepository.findById(todoId).orElse(null);
        assert todo != null;

        todo.update(todoRegisterForm);
        Todo updatedTodo = todoRepository.save(todo);
        updatedTodo.completeTodo(checkIsCompleted(todoRegisterForm));

        return modelMapper.map(updatedTodo, TodoDTO.class);
    }

    public void remove(Long todoId){
        todoRepository.deleteById(todoId);
    }
}
