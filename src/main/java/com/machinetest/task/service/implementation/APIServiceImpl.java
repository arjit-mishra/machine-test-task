package com.machinetest.task.service.implementation;

import com.machinetest.task.DTO.Matches;
import com.machinetest.task.DTO.TokenData;
import com.machinetest.task.service.APIService;
import com.machinetest.task.util.JwtUtil;
import com.machinetest.task.util.UserUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.*;

@Service
public class APIServiceImpl implements APIService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${resourceUrl}")
    private String resourceUrl;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Long getDrawMatchesCount() throws ResourceAccessException {

        Matches matches;


        try {

            //Get matches from resource URL.
            matches = Objects.requireNonNull(restTemplate.getForEntity(resourceUrl, Matches.class).getBody(), "Resource URL must not be Null");
        }
        catch (ResourceAccessException ex){
            throw new ResourceAccessException("Unable to connect to url "+resourceUrl);
        }

        return matches.getData().stream()
                .filter(match -> match.getTeam1goals().equals(match.getTeam2goals()))
                .count();

    }


    @Override
    public TokenData getTokenData(HttpServletRequest request){
        //get bearer token from Authorization header of HttpRequest
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userName;

        //get token form request
        jwt = authHeader.substring(7);

        //get username from token
        userName = jwtUtil.extractUsername(jwt);

        UserDetails userDetail = UserUtil.getUserDetail(userName);


        return TokenData.builder()
                .username(userName)
                .issuedAt(jwtUtil.extractIssuedAt(jwt).toString())
                .expiration(jwtUtil.extractExpiration(jwt).toString())
                .authorities(userDetail.getAuthorities())
                .build();
    }

    public Long getDrawMatchesCountThread() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future obj = executorService.submit(new Callable() {

            // Overriding the call method
            public Object call() throws InterruptedException {
                System.out.println("Connecting to remote resource in " + Thread.currentThread().getName());
                System.out.println("Thread is sleeping for " + getRandom());
                Thread.sleep(TimeUnit.SECONDS.toMillis(getRandom()));

                Matches matches;

                try {

                    //Get matches from resource URL.
                    matches = Objects.requireNonNull(restTemplate.getForEntity(resourceUrl, Matches.class).getBody(), "Resource URL must not be Null");
                }
                catch (ResourceAccessException ex){
                    throw new ResourceAccessException("Unable to connect to url "+resourceUrl);
                }

                return matches.getData().stream()
                        .filter(match -> match.getTeam1goals().equals(match.getTeam2goals()))
                        .count();
            }
        });

        executorService.shutdown();
        return (Long) obj.get();

    }

    //return a random between 3 and 6
    private Long getRandom(){
        return 3 + (long)(Math.random() * ((6 - 3) + 1));
    }



}
