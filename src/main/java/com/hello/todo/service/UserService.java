package com.hello.todo.service;

import com.hello.todo.domain.User;
import com.hello.todo.dto.SignUpForm;
import com.hello.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username);
    }

    public User signUp(SignUpForm signUpForm){
        User newUser = modelMapper.map(signUpForm, User.class);
        newUser.giveRole();
        return userRepository.save(newUser);
    }
}
