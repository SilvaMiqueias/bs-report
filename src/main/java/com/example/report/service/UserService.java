package com.example.report.service;

import com.example.report.dto.LoginDTO;
import com.example.report.dto.RecoveryJwtTokenDTO;
import com.example.report.dto.UserDTO;
import com.example.report.model.Role;
import com.example.report.model.User;
import com.example.report.repository.UserRepository;
import com.example.report.security.JwtTokenService;
import com.example.report.security.SecurityConfig;
import com.example.report.security.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private SecurityConfig securityConfig;


    public RecoveryJwtTokenDTO authenticateUser(LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImp userDetailsImp = (UserDetailsImp) authentication.getPrincipal();

        return new RecoveryJwtTokenDTO(jwtTokenService.generateToken(userDetailsImp));
    }

    public void createUser(UserDTO userDTO){
        User newUser = User.builder().username(userDTO.getUsername())
                .password(securityConfig.passwordEncoder().encode(userDTO.getPassword()))
                .roles(List.of(Role.builder().name(userDTO.getRole()).build()))
                .build();

        userRepository.save(newUser);
    }

}
