package com.example.buoi6.service.impl;

import com.example.buoi6.dto.StudentDTO;
import com.example.buoi6.entity.Student;
import com.example.buoi6.exception.InternalServerException;
import com.example.buoi6.exception.NotFoundException;
import com.example.buoi6.repository.StudentRepository;
import com.example.buoi6.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> {throw new NotFoundException("Not Found Student has ID: " + id);});
    }

    @Override
    public void updateStudent(Long id, StudentDTO studentDTO) {
        try {
            studentRepository.updateStudent(id, studentDTO.getName(), studentDTO.getAddress());
        } catch (Exception e) {
            throw new InternalServerException("Data error updating student");
        }
    }

    @Override
    public void deleteStudentById(Long id) {
        Optional<Student> studentFind = studentRepository.findById(id);

        if(studentFind.isEmpty()) {
            throw new NotFoundException("Not Found Student has ID: " + id);
        }

        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerException("Data error deleting student");
        }
    }
}
