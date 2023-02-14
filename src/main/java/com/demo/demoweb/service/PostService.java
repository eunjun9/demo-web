package com.demo.demoweb.service;

import com.demo.demoweb.domain.Post;
import com.demo.demoweb.repository.PostRepository;
import com.demo.demoweb.request.PostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostRequest postRequest) {
        // postRequest -> Entity
        Post post = new Post(postRequest.getTitle(), postRequest.getContent());
        postRepository.save(post);
    }
}
