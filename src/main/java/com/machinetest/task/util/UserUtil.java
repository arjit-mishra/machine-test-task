package com.machinetest.task.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;


/*
* Utility class to give 2 predefined Users
* */
public class UserUtil {


    public static List<UserDetails> getUsers(){

        List<UserDetails> userList = new ArrayList<>();

        userList.add(User.withUsername("admin")
                    .password("$2a$12$dadfCVQvDa30ucv/DXWn8OfWxK6Gztjdx/9Q9DroX0G3MnAVl9N4C")//admin
//                    .password("admin")
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .authorities(List.of(new SimpleGrantedAuthority("ROLE_ADMIN")))
                .build());

        userList.add(User.
                    withUsername("tester")
                    .password("$2a$12$6/LtenPqIQ/9e00mV5aGEuazEnG1AbTs03lasGoUIxhIkRm5.SIq2")//tester
//                    .password("tester")
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .authorities(List.of(new SimpleGrantedAuthority("ROLE_TESTER")))
                .build());

        return userList;
    }

    public static UserDetails getUserDetail(String userName){
        return UserUtil.getUsers().stream()
                .filter(u -> u.getUsername().equals(userName))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User name Not found"));
    }
}
