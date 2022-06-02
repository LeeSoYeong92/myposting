package com.sparta.spring_week02_work.dto;


import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.*;

@Getter
@Setter

public class SignupRequestDto {

    @NotBlank(message = "닉네임을 입력하세요")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,12}$", message = "영문자와 숫자를 섞어 3~12 자를 입력하세요")
    private String username;

    @NotBlank
    @Size(min=4, message = "4자 이상으로 설정해주세요")
    private String password;

    @NotBlank(message = "설정한 비밀번호를 재입력하세요")
    private String password_check;
}
