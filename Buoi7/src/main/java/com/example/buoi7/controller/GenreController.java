package com.example.buoi7.controller;

import com.example.buoi7.service.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("genres")
public class GenreController {
    @Autowired
    private IGenreService genreService;

    @GetMapping("/")
    public ResponseEntity<?> getListGenre() {
        return ResponseEntity.ok().body(genreService.getAll());
    }
}
