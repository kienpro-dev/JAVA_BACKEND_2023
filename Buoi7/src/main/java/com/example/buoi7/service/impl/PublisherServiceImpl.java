package com.example.buoi7.service.impl;

import com.example.buoi7.dto.PublisherDTO;
import com.example.buoi7.model.Author;
import com.example.buoi7.model.Publisher;
import com.example.buoi7.repo.AuthorRepository;
import com.example.buoi7.repo.PublisherRepository;
import com.example.buoi7.service.IPublisherService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PublisherServiceImpl implements IPublisherService {
    private final PublisherRepository publisherRepository;

    private final AuthorRepository authorRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    @Override
    public void createPublisher(PublisherDTO publisherDTO) {
        Optional<Author> authorFind = authorRepository.findById(publisherDTO.getAuthor_id());
        if(authorFind.isPresent()) {
            publisherRepository.save(modelMapper.map(publisherDTO, Publisher.class));
        }
    }
}
