package com.demo.demoweb.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc; // 웹 통신관련 테스트에 사용

    @Test
    @DisplayName("/gets 요청시 Hello World 출력")
    void getTest() throws Exception {
        // expected
        mockMvc.perform(MockMvcRequestBuilders.get("/gets") // url 요청에 대한 테스트
                        .param("title", "스터디")
                        .param("content", "mvc연습"))
                .andExpect(status().isOk()) // 기대하는 결과값 -> 맞는 값이 나오면 다음 실행
                .andExpect(content().string("Hello World"))
                .andDo(MockMvcResultHandlers.print()); // Ctrl + Q = 설명문서
    }

    @Test
    @DisplayName("/posts 요청시 Hello World 출력")
    void postTest() throws Exception {
        // expected
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"\", \"content\": \"Hello World\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(MockMvcResultHandlers.print()); // Ctrl + Q = 설명문서
    }
}