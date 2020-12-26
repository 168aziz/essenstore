package com.essenstore.api;

import com.essenstore.dto.AuthDto;
import com.essenstore.dto.RegisterUserDto;
import com.essenstore.service.AuthService;
import com.essenstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("login")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDto authDto) {
        var token = authService.authenticate(authDto);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, token)
                .build();
    }

    @PostMapping("register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterUserDto registerUserDto) {
        registerUserDto.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        userService.register(registerUserDto);
        return ResponseEntity.accepted().build();
    }



}
