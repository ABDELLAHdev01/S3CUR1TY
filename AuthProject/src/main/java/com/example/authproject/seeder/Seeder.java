package com.example.authproject.seeder;

import com.example.authproject.Repository.AuthorityRepository;
import com.example.authproject.Repository.PostRepository;
import com.example.authproject.Repository.RoleRepository;
import com.example.authproject.domain.Authority;
import com.example.authproject.domain.Post;
import com.example.authproject.domain.Role;
import com.example.authproject.enm.AuthorityEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.ArrayList;

@RequiredArgsConstructor
@Component
public class Seeder  implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final PostRepository postRepository;


    public void seedPosts() {
        List<Post> posts = List.of(
                Post.builder()
                        .text("Post 1")
                        .build(),
                Post.builder()
                        .text("Post 1")
                        .build()
        );

        postRepository.saveAll(posts);
    }
    public void seedAuthorities(){
        if (authorityRepository.findAll().isEmpty()){


        List<Authority> authorities = new ArrayList<>();
        for (AuthorityEnum authorityName : AuthorityEnum.values()) {
            Authority authority = Authority.builder()
                    .name(authorityName)
                    .build();
            authorities.add(authority);
        }
        authorityRepository.saveAll(authorities);
            System.out.println("authorities has been seed");
        }

    }

    public void seedRoles() {
        if (roleRepository.findAll().isEmpty()){


        List<Role> roles = List.of(
                Role.builder()
                        .name("ROLE_USER")
                        .build(),
                Role.builder()
                        .name("ROLE_ADMIN")
                        .authorities(authorityRepository.findAll())
                        .build()
        );

        roleRepository.saveAll(roles);
            System.out.println("roles has been seedd");
        }
    }

    @Override
    public void run(String... args) throws Exception {
        seedPosts();
        seedAuthorities();
        seedRoles();
    }
}
