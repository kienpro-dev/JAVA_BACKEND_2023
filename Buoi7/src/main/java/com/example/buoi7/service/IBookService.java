package com.example.buoi7.service;

import com.example.buoi7.dto.BookDTO;
import com.example.buoi7.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookService {
    List<Book> getAll();

    void createBook(BookDTO bookDTO);
}
