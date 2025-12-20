package org.example.zoopark.controller;

import lombok.RequiredArgsConstructor;
import org.example.zoopark.dto.LoginDto;
import org.example.zoopark.dto.UserDto;
import org.example.zoopark.entity.UserModel;
import org.example.zoopark.mapper.UserMapper;
import org.example.zoopark.service.MyUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final MyUserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        UserModel user = (UserModel) authentication.getPrincipal();
        return ResponseEntity.ok(userMapper.toDto(user));
    }


    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(user));
    }


}
