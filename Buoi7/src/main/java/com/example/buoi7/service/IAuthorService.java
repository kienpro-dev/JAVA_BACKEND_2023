package com.example.buoi7.service;

import com.example.buoi7.dto.AuthorDTO;
import com.example.buoi7.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAuthorService {
    List<Author> getAll();

    void createAuthor(AuthorDTO authorDTO);
}
