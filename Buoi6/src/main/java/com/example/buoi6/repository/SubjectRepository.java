package com.example.buoi6.repository;

import com.example.buoi6.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

//    @Query("select s from Subject s")
//    List<Subject> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query("select s.name from Subject s where s.student.id = ?1")
    List<Subject> findSubjectsByStudent(Long id);
}
