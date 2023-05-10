package com.example.buoi7.service;

import com.example.buoi7.dto.GenreDTO;
import com.example.buoi7.model.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IGenreService {
    List<Genre> getAll();

    void createGenre(GenreDTO genreDTO);
}
