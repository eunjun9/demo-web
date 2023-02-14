package com.demo.demoweb.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Alt + Insert = Generate 단축키
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Alt + J = 같은 단어 한 번에 선택 및 수정
    private String title;

    @Lob // Large object
    private String content;
}
