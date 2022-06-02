package com.sparta.spring_week02_work.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PostRequestDto {
    private String username;
    private String title;
    private String content;
    private String password;
}
