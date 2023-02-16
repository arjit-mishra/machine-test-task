package com.machinetest.task.exception;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.concurrent.ExecutionException;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ InsufficientAuthenticationException.class, MalformedJwtException.class, ExpiredJwtException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected String handleAuthenticationException(Exception ex) {

        return ex.getLocalizedMessage() + ". Token is either missing or expired or tampered with.";
    }
    @ExceptionHandler({Exception.class, ResourceAccessException.class, RuntimeException.class, IllegalArgumentException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected String handleException(Exception ex) {
        return ex.getLocalizedMessage();
    }

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected String handelBadToken(Exception ex) {
        return ex.getLocalizedMessage();
    }


    @ExceptionHandler({ExecutionException.class, InterruptedException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected String handelThreadException(Exception ex) {
        return ex.getLocalizedMessage();
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected String accessDenied(Exception ex) {
        return "You don't have access to this resource";
    }




}
