package com.example.authproject.service;

import com.example.authproject.domain.Authority;

import java.util.*;

public interface AuthorityService {


    Boolean hasAuthority(String authority);

    List<Authority> getAuthorities();




}
