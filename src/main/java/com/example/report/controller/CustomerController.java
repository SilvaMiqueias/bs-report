package com.example.report.controller;


import com.example.report.dto.UserPageDTO;
import com.example.report.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/report/auth/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("find-all-by-page")
    private ResponseEntity<UserPageDTO> findAllByPage(@RequestParam(value = "search", defaultValue = "") String search,
                                                      @RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "size", defaultValue = "10") int size){
        return ResponseEntity.ok(customerService.findAllCustomerByPage(search,page, size));
    }
}
