package com.sparta.spring_week02_work.service;


import com.sparta.spring_week02_work.dto.SignupRequestDto;
import com.sparta.spring_week02_work.model.Person;
import com.sparta.spring_week02_work.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto)  {
        String username = requestDto.getUsername();


         Optional<Person> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임 입니다");
        }

        String password = passwordEncoder.encode(requestDto.getPassword());
        String password_check = requestDto.getPassword_check();

        Person user = new Person(username, password, password_check);
        userRepository.save(user);
    }
}


