package com.example.authproject.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authority> authorities;

}
