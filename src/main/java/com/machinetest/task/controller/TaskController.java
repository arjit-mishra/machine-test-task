package com.machinetest.task.controller;


import com.machinetest.task.DTO.TokenData;
import com.machinetest.task.service.APIService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    APIService apiService;

    @GetMapping("/tokenData")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public TokenData getTokenData(@NonNull HttpServletRequest request){
        return apiService.getTokenData(request);
    }

    @GetMapping("/admin/drawMatchesCount")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Long getDrawMatchesCount(){
        return apiService.getDrawMatchesCount();
    }

    @GetMapping("/admin/drawMatchesCountThread")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Long getDrawMatchesCountThread() throws ExecutionException, InterruptedException {
        return apiService.getDrawMatchesCountThread();
    }

}
