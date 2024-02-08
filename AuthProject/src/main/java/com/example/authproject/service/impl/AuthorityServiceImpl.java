package com.example.authproject.service.impl;

import com.example.authproject.Repository.AuthorityRepository;
import com.example.authproject.domain.Authority;
import com.example.authproject.service.AuthorityService;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Boolean hasAuthority(String authority) {

        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return authorities.contains(authority);
    }

    @Override
    public List<Authority> getAuthorities() {
        return authorityRepository.findAll();
    }
}
