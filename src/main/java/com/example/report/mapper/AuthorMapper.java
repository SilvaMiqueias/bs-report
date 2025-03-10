package com.example.report.mapper;

import com.example.report.dto.AuthorDTO;
import com.example.report.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO authorToAuthorDTO(Author author);

    Author  authorDTOToAuthor(AuthorDTO authorDTO);
}
