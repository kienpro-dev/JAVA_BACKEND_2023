package com.example.btvn_buoi45.repository;

import com.example.btvn_buoi45.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    void deleteAccountByUsername(String username);

}
