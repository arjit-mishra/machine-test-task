package com.machinetest.task.service;

import com.machinetest.task.DTO.AuthRequest;
import com.machinetest.task.DTO.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);
}
