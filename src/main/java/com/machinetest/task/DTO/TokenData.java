package com.machinetest.task.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenData {
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    private String issuedAt;
    private String expiration;
}
