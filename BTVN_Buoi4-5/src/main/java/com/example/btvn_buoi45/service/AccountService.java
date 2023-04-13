package com.example.btvn_buoi45.service;

import com.example.btvn_buoi45.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    List<Account> findAll();

    Account findAccountByUsername(String username);

    void createAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(String username);
}
