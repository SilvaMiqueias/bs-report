package com.example.report.controller;

import com.example.report.dto.BookDTO;
import com.example.report.dto.BookPageDTO;
import com.example.report.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/report/auth/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/find-all")
    private ResponseEntity<List<BookDTO>> findAll(){
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/find-all-by-page")
    private ResponseEntity<BookPageDTO> findAllByPage(@RequestParam(value = "search", defaultValue = "") String search,
                                                      @RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "size", defaultValue = "10") int size){
        return ResponseEntity.ok(bookService.findAllByPage(search,page, size));
    }

    @GetMapping("/find-by/{id}")
    private ResponseEntity<BookDTO> findBy(@PathVariable Long id){
        return ResponseEntity.ok(bookService.findBy(id));
    }

    @PostMapping("/create")
    private ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDTO) throws IllegalAccessException {
        return ResponseEntity.ok(bookService.create(bookDTO));
    }

    @PutMapping("/update")
    private ResponseEntity<BookDTO> update(@RequestBody BookDTO bookDTO) throws IllegalAccessException {
        return ResponseEntity.ok(bookService.update(bookDTO));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id){
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }
}
