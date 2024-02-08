package com.example.authproject.auth;

import com.example.authproject.Repository.RoleRepository;
import com.example.authproject.Repository.UserRepository;
import com.example.authproject.config.JwtService;
import com.example.authproject.domain.Authority;
import com.example.authproject.domain.Role;
import com.example.authproject.domain.User;
import com.example.authproject.enm.AuthorityEnum;
import com.example.authproject.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final JwtService jwtService;
   private final AuthenticationManager authenticationManager;
   private final AuthorityService authorityService;
   private final RoleRepository roleRepository;
    public AuthenticationCResponse register(RegisterRequest registerRequest) {
        if (!userRepository.findByEmail(registerRequest.getEmail()).isEmpty()) {
            throw new RuntimeException("Email already exists");
        }
        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .role(roleRepository.findByName("ROLE_USER").get())
                .email(registerRequest.getEmail())

                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();


        userRepository.save(user);
        var JwtToken = jwtService.generateToken(user);
        return AuthenticationCResponse.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .token(JwtToken).build();
    }

    public AuthenticationCResponse authenticate(AuthenticateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository.findByEmailNativeQuery(request.getEmail()).orElseThrow();
        var JwtToken = jwtService.generateToken(user);
        return AuthenticationCResponse.builder().token(JwtToken).build();

    }
}
