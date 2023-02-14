package com.demo.demoweb.controller;

import com.demo.demoweb.domain.Post;
import com.demo.demoweb.repository.PostRepository;
import com.demo.demoweb.request.PostRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc; // 웹 통신관련 테스트에 사용

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        // 각각의 테스트 메소드들이 실행되기 전에 항상 실행되는 메소드
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("/gets 요청시 Hello World 출력")
    void getTest() throws Exception {
        // expected
        mockMvc.perform(MockMvcRequestBuilders.get("/gets") // url 요청에 대한 테스트
                        .param("title", "스터디")
                        .param("content", "mvc연습"))
                .andExpect(status().isOk()) // 기대하는 결과값 -> 맞는 값이 나오면 다음 실행
                .andExpect(content().string("Hello World"))
                .andDo(print()); // Ctrl + Q = 설명문서
    }

    @Test
    @DisplayName("/posts 요청시 Hello World 출력")
    void postTest() throws Exception {
        // given
        PostRequest request = new PostRequest("제목입니다.", "내용입니다.");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(request);

        System.out.println(json); // sout

        // expected
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.code").value("400"))
//                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
//                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(print()); // Ctrl + Q = 설명문서
    }

    @Test
    @DisplayName("/posts 요청시 DB에 값이 저장된다.")
    void postTest2() throws Exception {
        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"제목입니다\", \"content\": \"내용입니다.\"}"))
                .andExpect(status().isOk())
                .andDo(print());

        // then
        Assertions.assertEquals(1L, postRepository.count());

        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }
}