package com.example.buoi6.service;

import com.example.buoi6.entity.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService {
    List<Subject> findSubjectsByStudentId(Long id);
}
