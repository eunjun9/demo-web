package com.demo.demoweb.controller;

import com.demo.demoweb.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {

        ErrorResponse response = new ErrorResponse("400", "잘못된 요청입니다.");
        
        for(FieldError fieldError : e.getFieldErrors()) {
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
            // Ctrl + Shift + Enter = 스마트 자동완성 (괄호, 세미콜론 등 자동으로 추가해줌)
            // Alt + Enter = 오류 해결 방법 제시
        }

        return response;
    }
}
