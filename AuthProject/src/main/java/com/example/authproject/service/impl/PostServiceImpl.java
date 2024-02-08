package com.example.authproject.service.impl;

import com.example.authproject.Repository.PostRepository;
import com.example.authproject.domain.Post;
import com.example.authproject.service.AuthorityService;
import com.example.authproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final AuthorityService authorityService;


    @Override
    public Post savePost(Post post) {
        if (!authorityService.hasAuthority("CAN_ADD")) {
            throw new RuntimeException("You don't have permission to save a post");
        }

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return postRepository.save(post);
    }
}
