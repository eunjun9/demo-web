package com.demo.demoweb.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class PostRequest {
    // Alt + Insert = 생성자, 게터세터 등 생성

    @NotBlank(message = "타이틀을 입력해주세요.") // 값이 Blank일 때 출력할 메세지
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

}
