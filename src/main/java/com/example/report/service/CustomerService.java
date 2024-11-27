package com.example.report.service;

import com.example.report.dto.CategoryPageDTO;
import com.example.report.dto.UserDTO;
import com.example.report.dto.UserPageDTO;
import com.example.report.mapper.UserMapper;
import com.example.report.model.Category;
import com.example.report.model.User;
import com.example.report.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private UserRepository repository;
    public UserPageDTO findAllCustomerByPage(String search, Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = repository.findAllCustomerByPage(search.toUpperCase(), pageable);

        return UserPageDTO.builder().content(userPage.getContent().stream().map(UserMapper.INSTANCE::userToUserDTO).collect(Collectors.toList())).totalPages(userPage.getTotalPages())
                .totalElements(userPage.getTotalElements()).pageNumber(userPage.getNumber()).pageSize(userPage.getSize()).build();
    }

}
