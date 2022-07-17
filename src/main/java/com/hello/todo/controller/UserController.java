package com.hello.todo.controller;

import com.hello.todo.config.JwtTokenProvider;
import com.hello.todo.domain.User;
import com.hello.todo.dto.LoginForm;
import com.hello.todo.dto.SignUpForm;
import com.hello.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/sign-up")
    public User signUp(@RequestBody SignUpForm signUpForm){
        return userService.signUp(signUpForm);
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginForm loginForm, HttpServletResponse response){
        User user = (User) userService.loadUserByUsername(loginForm.getName());
        if(user == null){
            return null;
        }
        String token = jwtTokenProvider.createTokenLogin(user.getName(), user.getRole());
        response.setHeader("apiKeys", token);
        return user;
    }
}
