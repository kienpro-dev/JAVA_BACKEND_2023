package com.example.buoi7.service;

import com.example.buoi7.dto.PublisherDTO;
import com.example.buoi7.model.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPublisherService {
    List<Publisher> getAll();

    void createPublisher(PublisherDTO publisherDTO);
}
