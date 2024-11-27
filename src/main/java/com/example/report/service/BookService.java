package com.example.report.service;

import com.example.report.dto.BookDTO;
import com.example.report.dto.BookPageDTO;
import com.example.report.mapper.AuthorMapper;
import com.example.report.mapper.BookMapper;
import com.example.report.mapper.CategoryMapper;
import com.example.report.model.Book;
import com.example.report.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<BookDTO> findAll(){
        return repository.findAll().stream().map(BookMapper.INSTANCE::bookToBookDTO).collect(Collectors.toList());
    }

    public BookPageDTO findAllByPage(String search, Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = repository.findAllByPage(search.toUpperCase(), pageable);
        return BookPageDTO.builder().content(bookPage.getContent().stream().map(BookMapper.INSTANCE::bookToBookDTO).collect(Collectors.toList()))
                .totalPages(bookPage.getTotalPages()).totalElements(bookPage.getTotalElements())
                .pageNumber(bookPage.getNumber()).pageSize(bookPage.getSize()).build();
    }

    public BookDTO findBy(Long id){
        return BookMapper.INSTANCE.bookToBookDTO(repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Livro não encontrado!")));
    }

    public BookDTO create(BookDTO bookDTO) throws IllegalAccessException {
        Book book = Book.builder().title(bookDTO.getTitle()).description(bookDTO.getDescription()).price(bookDTO.getPrice())
                .categories(bookDTO.getCategories().stream().map(CategoryMapper.INSTANCE::categoryDTOToCategory).collect(Collectors.toList()))
                .authors(bookDTO.getAuthors().stream().map(AuthorMapper.INSTANCE::authorDTOToAuthor).collect(Collectors.toList()))
                .qtd(bookDTO.getQtd()).language(bookDTO.getLanguage()).releaseDate(bookDTO.getReleaseDate()).page(bookDTO.getPage()).image(bookDTO.getImage())
                .build();

        return BookMapper.INSTANCE.bookToBookDTO(repository.save(book));
    }

    public BookDTO update(BookDTO bookDTO) throws IllegalAccessException {
       Book book =  repository.findById(bookDTO.getId()).orElseThrow( () ->  new IllegalAccessException("Livro não encontrado"));
       Book bookUpdate =  BookMapper.INSTANCE.bookDTOToBook(bookDTO);
       if(book.equals(bookUpdate)) return  BookMapper.INSTANCE.bookToBookDTO(book);
       
       bookUpdate.setCode(book.getCode());
       bookUpdate.setActive(true);
       return BookMapper.INSTANCE.bookToBookDTO(repository.save(bookUpdate));
    }

    public void delete(Long id){
        Book book =  repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Livro não encontrado!"));
        book.setActive(false);
        repository.save(book);
    }

    public byte[] decodeImage(String base64String) {
        String base64Data = base64String;
        if (base64String.startsWith("data:image/png;base64,")) {
            base64Data = base64String.replace("data:image/png;base64,", "");
        }
        return Base64.getDecoder().decode(base64Data);
    }


}
