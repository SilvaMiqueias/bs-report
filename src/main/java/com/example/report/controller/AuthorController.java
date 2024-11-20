package com.example.report.controller;

import com.example.report.dto.AuthorDTO;
import com.example.report.dto.AuthorPageDTO;
import com.example.report.dto.CategoryDTO;
import com.example.report.dto.CategoryPageDTO;
import com.example.report.service.AuthorService;
import com.example.report.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/report/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("find-all")
    private ResponseEntity<List<AuthorDTO>> findAll(){
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("find-all-by-page")
    private ResponseEntity<AuthorPageDTO> findAllByPage(@RequestParam(value = "search", defaultValue = "") String search,
                                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size){
        return ResponseEntity.ok(authorService.findAllByPage(search,page, size));
    }

    @GetMapping("/find-by/{id}")
    private ResponseEntity<AuthorDTO> findBy(@PathVariable Long id){
        return ResponseEntity.ok(authorService.findBy(id));
    }

    @PostMapping("/create")
    private ResponseEntity<AuthorDTO> create(@RequestBody AuthorDTO authorDTO) throws IllegalAccessException {
        return ResponseEntity.ok(authorService.create(authorDTO));
    }

    @PutMapping("/update")
    private ResponseEntity<AuthorDTO> update(@RequestBody AuthorDTO authorDTO) throws IllegalAccessException {
        return ResponseEntity.ok(authorService.update(authorDTO));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id){
         authorService.delete(id);
         return ResponseEntity.ok().build();
    }
}
