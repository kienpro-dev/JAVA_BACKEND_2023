package com.example.buoi7.service.impl;

import com.example.buoi7.dto.BookDTO;
import com.example.buoi7.model.Author;
import com.example.buoi7.model.Book;
import com.example.buoi7.model.Genre;
import com.example.buoi7.repo.AuthorRepository;
import com.example.buoi7.repo.BookRepository;
import com.example.buoi7.repo.GenreRepository;
import com.example.buoi7.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class BookServiceImpl implements IBookService {
    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public void createBook(BookDTO bookDTO) {
        Optional<Author> authorFind = authorRepository.findById(bookDTO.getAuthor_id());
        Optional<Genre> genreFind = genreRepository.findById(bookDTO.getGenre_id());

        if(genreFind.isPresent() && authorFind.isPresent()) {
            bookRepository.save(modelMapper.map(bookDTO, Book.class));
        }
    }
}
