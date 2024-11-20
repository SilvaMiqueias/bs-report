package com.example.report.service;

import com.example.report.dto.CategoryDTO;
import com.example.report.dto.CategoryPageDTO;
import com.example.report.mapper.CategoryMapper;
import com.example.report.model.Category;
import com.example.report.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<CategoryDTO> findAll(){
        return repository.findAll().stream().map(CategoryMapper.INSTANCE::categoryToCategoryDTO).collect(Collectors.toList());
    }

    public CategoryPageDTO findAllByPage(String search, Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = repository.findAllByPage(search.toUpperCase(), pageable);
        return CategoryPageDTO.builder().content(categoryPage.getContent()).totalPages(categoryPage.getTotalPages()).totalElements(categoryPage.getTotalElements())
                .pageNumber(categoryPage.getNumber()).pageSize(categoryPage.getSize()).build();
    }

    public CategoryDTO findBy(Long id){
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada!")));
    }

    public CategoryDTO create(CategoryDTO categoryDTO) throws IllegalAccessException {
        if(repository.existsByNameNotIgnoreCase(categoryDTO.getName()))
            throw new IllegalAccessException("O nome dado para essa categoria já está cadastrado!");

        Category category = Category.builder().name(categoryDTO.getName()).description(categoryDTO.getDescription()).build();
        category = repository.save(category);
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(category);
    }

    public CategoryDTO update(CategoryDTO categoryDTO) throws IllegalAccessException {
        Category category = repository.findById(categoryDTO.getId()).orElse(null);
        if(category != null)
            if(repository.existsByNameNotIgnoreCase(categoryDTO.getName()))
                if(!category.getName().equalsIgnoreCase(categoryDTO.getName()) && category.getId().equals(categoryDTO.getId()))
                    throw new IllegalAccessException("O nome dado para essa categoria já está cadastrado!");

        category = repository.save(CategoryMapper.INSTANCE.categoryDTOToCategory(categoryDTO));
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(category);
    }

    public void delete(Long id){
      Category category =  repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada!"));
      repository.delete(category);
    }
}
