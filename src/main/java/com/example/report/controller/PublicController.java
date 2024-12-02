package com.example.report.controller;

import com.example.report.dto.AuthorDTO;
import com.example.report.dto.BookDTO;
import com.example.report.dto.CategoryDTO;
import com.example.report.service.AuthorService;
import com.example.report.service.BookService;
import com.example.report.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("report/public")
public class PublicController {


    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;


    @GetMapping("/book/find-all")
    private ResponseEntity<List<BookDTO>> findAllBook(){
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/category/find-all")
    private ResponseEntity<List<CategoryDTO>> findAllCategory(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/author/find-all")
    private ResponseEntity<List<AuthorDTO>> findAllAuthor(){
        return ResponseEntity.ok(authorService.findAll());
    }

}
