package com.example.authproject.auth;

import com.example.authproject.Repository.UserRepository;
import com.example.authproject.config.JwtService;
import com.example.authproject.domain.User;
import com.example.authproject.enm.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final JwtService jwtService;
   private final AuthenticationManager authenticationManager;
    public AuthenticationCResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();


        userRepository.save(user);
        var JwtToken = jwtService.generateToken(user);
        return AuthenticationCResponse.builder().token(JwtToken).build();
    }

    public AuthenticationCResponse authenticate(AuthenticateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var JwtToken = jwtService.generateToken(user);
        return AuthenticationCResponse.builder().token(JwtToken).build();

    }
}
