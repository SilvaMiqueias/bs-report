package com.example.report.mapper;

import com.example.report.dto.CategoryDTO;
import com.example.report.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);

    Category  categoryDTOToCategory(CategoryDTO categoryDTO);

}
