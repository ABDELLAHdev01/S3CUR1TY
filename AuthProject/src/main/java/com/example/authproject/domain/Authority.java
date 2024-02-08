package com.example.authproject.domain;

import com.example.authproject.enm.AuthorityEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties()
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "authorities")
    private List<Role> roles;

    @Enumerated(EnumType.STRING)
    private AuthorityEnum name;


}
