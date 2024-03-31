package com.example.sales.system.controller;

import com.example.sales.system.domain.entities.JpaUser;
import com.example.sales.system.repository.UserRepository;
import com.example.sales.system.service.JwtService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Tag(name = "User")
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService,
                          AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody JpaUser request)
    {
        JpaUser user = new JpaUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        String token = jwtService.generateKey(user);
//        UserDto t = new UserDto();
//        t.setUsername(token);
        return ResponseEntity.ok(token);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JpaUser request)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),request.getPassword()
                )
        );

        JpaUser user = userRepository.findByUsername(request.getUsername());
        String token = jwtService.generateKey(user);
       // UserDto t = new UserDto();
        //t.setUsername(token);
        return ResponseEntity.ok(token);

    }
}
