package com.essenstore.api;

import com.essenstore.dto.AuthDto;
import com.essenstore.dto.RegisterUserDto;
import com.essenstore.entity.User;
import com.essenstore.service.ActivationService;
import com.essenstore.service.AuthService;
import com.essenstore.service.EmailService;
import com.essenstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import static com.essenstore.utils.Utils.generateCode;
import static com.essenstore.utils.Utils.getMail;

@Slf4j
@Validated
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final EmailService mailSenderService;

    private final ActivationService activationService;

    private final ModelMapper modelMapper;

    @Value("${app.url}")
    private String url;

    @PostMapping("login")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDto authDto) {
        var token = authService.authenticate(authDto);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, token)
                .build();
    }

    @PostMapping("register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterUserDto registerUserDto) {
        registerUserDto.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        var user = userService.register(modelMapper.map(registerUserDto, User.class));
        var code = generateCode(user, url);

        mailSenderService.send(getMail(code));
        activationService.save(code);

        return ResponseEntity.accepted().build();
    }

    @PostMapping("{code}")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<?> activate(@PathVariable @NotBlank String code) {
        activationService.activate(code);
        return ResponseEntity.accepted().build();
    }

}
