package com.example.authproject.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationCResponse {
    private String token;
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;


}
