package com.example.buoi7.controller;

import com.example.buoi7.service.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authors")
public class AuthorController {
    @Autowired
    private IAuthorService authorService;

    @GetMapping("/")
    public ResponseEntity<?> getListAuthor() {
        return ResponseEntity.ok().body(authorService.getAll());
    }
}
