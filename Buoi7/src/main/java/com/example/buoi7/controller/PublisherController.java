package com.example.buoi7.controller;

import com.example.buoi7.service.IPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("publishers")
public class PublisherController {
    @Autowired
    private IPublisherService publisherService;

    @GetMapping("/")
    public ResponseEntity<?> getListPublisher() {
        return ResponseEntity.ok().body(publisherService.getAll());
    }
}
