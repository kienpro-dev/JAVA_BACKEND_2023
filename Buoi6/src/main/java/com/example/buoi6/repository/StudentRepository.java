package com.example.buoi6.repository;

import com.example.buoi6.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Transactional
    @Modifying
    @Query("update Student s set s.name = ?2, s.address = ?3 where s.id = ?1")
    void updateStudent(Long id, String name, String address);
}
