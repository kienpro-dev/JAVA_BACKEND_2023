package com.example.buoi6.controller;

import com.example.buoi6.dto.StudentDTO;
import com.example.buoi6.dto.SubjectDTO;
import com.example.buoi6.entity.Student;
import com.example.buoi6.entity.Subject;
import com.example.buoi6.exception.NotFoundException;
import com.example.buoi6.repository.StudentRepository;
import com.example.buoi6.repository.SubjectRepository;
import com.example.buoi6.service.StudentService;
import com.example.buoi6.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("")
    public ResponseEntity<?> getListSubject() {
        return ResponseEntity.ok(subjectRepository.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSubject(@RequestBody SubjectDTO subjectDTO) {
        Student student = studentService.findStudentById(subjectDTO.getStudent_id());

        Subject subject = new Subject(subjectDTO.getName(), student);

        return ResponseEntity.ok(subjectRepository.save(subject));
    }

    @GetMapping("/student-id/{id}")
    public ResponseEntity<?> getListSubjectByStudentId(@PathVariable Long id) {
        return ResponseEntity.ok(subjectService.findSubjectsByStudentId(id));
    }

}
