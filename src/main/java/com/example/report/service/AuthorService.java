package com.example.report.service;

import com.example.report.dto.AuthorDTO;
import com.example.report.dto.AuthorPageDTO;
import com.example.report.mapper.AuthorMapper;
import com.example.report.model.Author;
import com.example.report.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;


    public List<AuthorDTO> findAll(){
        return repository.findAll().stream().map(AuthorMapper.INSTANCE::authorToAuthorDTO).collect(Collectors.toList());
    }

    public AuthorPageDTO findAllByPage(String search, Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Author> authorPage = repository.findAllByPage(search.toUpperCase(), pageable);
        return AuthorPageDTO.builder().content(authorPage.getContent().stream().map(AuthorMapper.INSTANCE::authorToAuthorDTO).collect(Collectors.toList()))
                .totalPages(authorPage.getTotalPages()).totalElements(authorPage.getTotalElements())
                .pageNumber(authorPage.getNumber()).pageSize(authorPage.getSize()).build();
    }

    public AuthorDTO findBy(Long id){
        return AuthorMapper.INSTANCE.authorToAuthorDTO(repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Autor(a) não encontrado(a)!")));
    }

    public AuthorDTO create(AuthorDTO authorDTO) throws IllegalAccessException {
        if(repository.existsByNameNotIgnoreCase(authorDTO.getName()))
            throw new IllegalAccessException("O nome dado para esse(a) autor(a) já está cadastrado!");

        Author author = Author.builder().name(authorDTO.getName()).description(authorDTO.getDescription()).build();
        author = repository.save(author);
        return AuthorMapper.INSTANCE.authorToAuthorDTO(author);
    }

    public AuthorDTO update(AuthorDTO authorDTO) throws IllegalAccessException {
        Author author = repository.findById(authorDTO.getId()).orElse(null);
        if(author != null)
            if(repository.existsByNameNotIgnoreCase(authorDTO.getName()))
                if(!author.getName().equalsIgnoreCase(authorDTO.getName()) && author.getId().equals(authorDTO.getId()))
                    throw new IllegalAccessException("O nome dado para esse(a) autor(a)  já está cadastrado!");

        author = repository.save(AuthorMapper.INSTANCE.authorDTOToAuthor(authorDTO));
        return AuthorMapper.INSTANCE.authorToAuthorDTO(author);
    }

    public void delete(Long id){
        Author author =  repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Autor(a) não encontrado(a)!"));
        repository.delete(author);
    }

}
