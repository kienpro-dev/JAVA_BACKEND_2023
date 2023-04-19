package com.example.buoi6.service.impl;

import com.example.buoi6.entity.Subject;
import com.example.buoi6.exception.NotFoundException;
import com.example.buoi6.repository.SubjectRepository;
import com.example.buoi6.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Subject> findSubjectsByStudentId(Long id) {
        List<Subject> subjectList = subjectRepository.findSubjectsByStudent(id);

        if(subjectList.isEmpty()) {
            throw new NotFoundException("Not Found Subjects of Student has ID: " + id);
        }

        return subjectList;
    }
}
