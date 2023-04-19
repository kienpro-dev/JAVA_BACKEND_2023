package com.example.buoi6.repository;

import com.example.buoi6.dto.PhoneDTO;
import com.example.buoi6.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    @Transactional
    @Modifying
    @Query("update Phone p set p.name = ?2, p.brand = ?3, p.student.id = ?4 where p.id = ?1")
    void updatePhone(Long id, String name, String brand, Long student_id);
}
