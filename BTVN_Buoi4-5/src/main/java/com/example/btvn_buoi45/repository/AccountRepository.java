package com.example.btvn_buoi45.repository;

import com.example.btvn_buoi45.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Transactional
    @Modifying
    @Query("update Account a set a.password = ?2, a.email = ?3, a.updatedAt = current_timestamp where a.username = ?1")
    void updateAccount(String username, String password, String email);
}
