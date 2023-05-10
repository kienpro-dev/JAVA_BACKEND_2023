package com.example.buoi7.service.impl;

import com.example.buoi7.dto.AuthorDTO;
import com.example.buoi7.model.Author;
import com.example.buoi7.repo.AuthorRepository;
import com.example.buoi7.service.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AuthorServiceImpl implements IAuthorService {
    private final AuthorRepository authorRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public void createAuthor(AuthorDTO authorDTO) {
        authorRepository.save(modelMapper.map(authorDTO, Author.class));
    }
}
