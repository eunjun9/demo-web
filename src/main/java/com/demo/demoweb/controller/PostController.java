package com.demo.demoweb.controller;

import com.demo.demoweb.request.PostRequest;
import com.demo.demoweb.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController // JSON으로 통신할 때 사용 (Controller + ResponseBody)
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // Http Method
    // GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT

    @GetMapping("/gets") // google.com/?title=study&content=mvc연습
    public String get(@RequestParam String title, @RequestParam String content) {
        // Ctrl + Shift + Enter = 중괄호 자동 생성
        log.info("title={}, content={}", title, content);
        return "Hello World"; // Ctrl + Shift + T = 테스트클래스 자동 생성
    }

    // 글 등록
    @PostMapping("/posts")
    public Map<String, String> post(@RequestBody @Valid PostRequest param) /*throws Exception*/ {
        // 데이터를 검증하는 이유
        // 1. client 개발자가 깜빡할 수 있다. 실수로 값을 안 보낼 수 있다.
        // 2. client bug로 값이 누락될 수 있다.
        // 3. 외부에 나쁜 사람이 값을 임의로 조작해서 보낼 수 있다.
        // 4. DB에 값을 저장할 때 의도치 않은 오류가 발생할 수 있다.
        // 5. 서버 개발자의 편안함을 위해서
        // log.info("params = {}", params.toString());

        /*
        String title = postRequest.getTitle();
        if(title == null || title.equals("")) {
            // 1. 빡세다 (노가다)
            // 2. 개밭팁 -> 무언가 3번이상 반복작업을 할 때 내가 뭔가 잘못하고 있는건 아닐지 의심해본다.
            // 3. 누락 가능성
            // 4. 생각보다 검증해야할 게 많다 (꼼꼼하지 않을 수 있다)
            // 5. 뭔가 개발자스럽지 않다 (간지X)
            throw new Exception("타이틀값이 없어요!");
        } */

        // 1. 매번 메서드마다 값을 검증해야 한다.
        //    > 개발자가 까먹을 수 있다.
        //    > 검증 부분에서 버그가 발생할 여지가 높다.
        //    > 지겹다. (간지가 안난다)
        // 2. 응답값에 HashMap -> 응답 클래스를 만들어주는 게 좋다
        // 3. 여러 개의 에러처리가 힘듬
        // 4. 세 번 이상의 반복적인 작업은 피해야 한다.
        //    > 코드 && 개발에 관한 모든 것
        //      > 자동화 고려
        /*
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);
            String fieldName = firstFieldError.getField(); // title
            String errorMessage = firstFieldError.getDefaultMessage(); // ..에러 메시지

            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessage);
            return error;
        } */

        postService.write(param);
        return Map.of();
        // return params.getTitle() + ", " + params.getContent();
    }
}
