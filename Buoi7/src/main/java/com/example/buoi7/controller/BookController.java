package com.example.buoi7.controller;

import com.example.buoi7.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping("/")
    public ResponseEntity<?> getListBook() {
        return ResponseEntity.ok().body(bookService.getAll());
    }
}
