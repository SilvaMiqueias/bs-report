package com.example.report.security;

import com.example.report.model.User;
import com.example.report.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String token = recoveryToken(request);

            if(token != null){
                String subject = jwtTokenService.getSubjectFromToken(token);
                User user = userRepository.findByUsername(subject).get();
                UserDetailsImp userDetailsImp = new UserDetailsImp(user);

                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetailsImp.getUsername(), null, userDetailsImp.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                throw new RuntimeException("O token está ausente");
            }

        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
