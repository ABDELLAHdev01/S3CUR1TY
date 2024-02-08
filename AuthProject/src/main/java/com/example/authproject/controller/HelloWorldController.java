package com.example.authproject.controller;

import com.example.authproject.domain.Post;
import com.example.authproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloWorldController {

    private final PostService postService;
    @GetMapping
    public String hello() {
        Post post = Post.builder()
                .text("Hello World")
                .build();
        postService.savePost(post);
        return post.getText();
    }
}
