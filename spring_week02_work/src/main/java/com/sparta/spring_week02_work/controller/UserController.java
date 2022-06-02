package com.sparta.spring_week02_work.controller;


import com.sparta.spring_week02_work.dto.SignupRequestDto;
import com.sparta.spring_week02_work.model.Person;
import com.sparta.spring_week02_work.repository.UserRepository;
import com.sparta.spring_week02_work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;


@Controller

public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    //회원가입 페이지(아이디 중복체크)
    @PostMapping("/user/signup")
    public String registerUser(@Valid @ModelAttribute("requestDto") SignupRequestDto requestDto, BindingResult bindingResult){

       Optional<Person> found1 = userRepository.findByUsername(requestDto.getUsername());
        if(found1.isPresent()){
            FieldError fieldError = new FieldError("requestDto", "username", "중복된 아이디입니다.");
           bindingResult.addError(fieldError);
        }

        if(!requestDto.getPassword().equals(requestDto.getPassword_check())){
            FieldError fieldError = new FieldError("requestDto", "password_check", "비밀번호가 일치하지 않습니다.");
            bindingResult.addError(fieldError);
        }

        if(requestDto.getPassword().contains(requestDto.getUsername())){
            FieldError fieldError = new FieldError("requestDto", "password", "비밀번호에 닉네임이 포함되어있습니다.");
            bindingResult.addError(fieldError);
        }

        if(bindingResult.hasErrors()){
            return "signup";
        }

        else{
        userService.registerUser(requestDto);
        System.out.println(requestDto.getUsername());
        return "login";
    }
}


}
