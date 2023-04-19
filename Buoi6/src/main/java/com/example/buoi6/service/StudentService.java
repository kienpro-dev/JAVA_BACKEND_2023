package com.example.buoi6.service;

import com.example.buoi6.dto.StudentDTO;
import com.example.buoi6.entity.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    Student findStudentById(Long id);

    void updateStudent(Long id, StudentDTO studentDTO);

    void deleteStudentById(Long id);
}
