package com.example.buoi7.service.impl;

import com.example.buoi7.dto.GenreDTO;
import com.example.buoi7.model.Genre;
import com.example.buoi7.repo.GenreRepository;
import com.example.buoi7.service.IGenreService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GenreServiceImpl implements IGenreService {
    private final GenreRepository genreRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public void createGenre(GenreDTO genreDTO) {
        genreRepository.save(modelMapper.map(genreDTO, Genre.class));
    }
}
