package com.example.report.controller;

import com.example.report.dto.CategoryDTO;
import com.example.report.dto.CategoryPageDTO;
import com.example.report.model.Category;
import com.example.report.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/report/auth/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("find-all")
    private ResponseEntity<List<CategoryDTO>> findAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("find-all-by-page")
    private ResponseEntity<CategoryPageDTO> findAllByPage(@RequestParam(value = "search", defaultValue = "") String search,
                                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "size", defaultValue = "10") int size){
        return ResponseEntity.ok(categoryService.findAllByPage(search,page, size));
    }

    @GetMapping("/find-by/{id}")
    private ResponseEntity<CategoryDTO> findBy(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findBy(id));
    }

    @PostMapping("/create")
    private ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO) throws IllegalAccessException {
        return ResponseEntity.ok(categoryService.create(categoryDTO));
    }

    @PutMapping("/update")
    private ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO categoryDTO) throws IllegalAccessException {
        return ResponseEntity.ok(categoryService.update(categoryDTO));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id){
         categoryService.delete(id);
         return ResponseEntity.ok().build();
    }
}
