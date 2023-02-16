package com.machinetest.task.service;

import com.machinetest.task.DTO.Data;
import com.machinetest.task.DTO.Matches;
import com.machinetest.task.DTO.TokenData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface APIService {
    Long getDrawMatchesCount();
    TokenData getTokenData(HttpServletRequest request);

    public Long getDrawMatchesCountThread() throws ExecutionException, InterruptedException;
}
