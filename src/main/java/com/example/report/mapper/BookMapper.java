package com.example.report.mapper;

import com.example.report.dto.BookDTO;
import com.example.report.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper{

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO bookToBookDTO(Book book);

    Book  bookDTOToBook(BookDTO bookDTO);
}
