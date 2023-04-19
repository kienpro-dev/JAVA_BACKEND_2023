package com.example.buoi6.controller;

import com.example.buoi6.dto.StudentDTO;
import com.example.buoi6.entity.Student;
import com.example.buoi6.repository.StudentRepository;
import com.example.buoi6.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public ResponseEntity<?> getListStudent() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@RequestBody StudentDTO studentDTO) {
        Student student = new Student(studentDTO.getName(), studentDTO.getAddress());

        return ResponseEntity.ok(studentRepository.save(student));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id,@RequestBody StudentDTO studentDTO) {
        Student studentFind = studentService.findStudentById(id);
        studentFind.setName(studentDTO.getName());
        studentFind.setAddress(studentDTO.getAddress());
        studentService.updateStudent(id, studentDTO);
        return ResponseEntity.ok().body(studentFind);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok().build();
    }


}
