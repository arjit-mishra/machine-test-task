package com.machinetest.task.service.implementation;

import com.machinetest.task.DTO.AuthRequest;
import com.machinetest.task.DTO.AuthResponse;
import com.machinetest.task.service.AuthService;
import com.machinetest.task.util.JwtUtil;
import com.machinetest.task.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword()
        ));

        //if authentication successful, get token and build the response.
        return AuthResponse.builder()
                .token(jwtUtil.generateToken(UserUtil.getUserDetail(authRequest.getUsername())))
                .build();
    }
}
