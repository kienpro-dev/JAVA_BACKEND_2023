package com.example.btvn_buoi45.service;

import com.example.btvn_buoi45.entity.Account;
import com.example.btvn_buoi45.exception.InternalServerException;
import com.example.btvn_buoi45.exception.NotFoundException;
import com.example.btvn_buoi45.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findAccountByUsername(String username) {
        Optional<Account> accountFind = accountRepository.findById(username);

        if(accountFind.isEmpty()) {
            throw new NotFoundException("Not found account has username " + username);
        }

        return accountFind.get();
    }

    @Override
    public Account createAccount(Account account) {
        try {
            return accountRepository.save(account);
        } catch (Exception e) {
            throw new InternalServerException("Data error creating account");
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            accountRepository.updateAccount(account.getUsername(), account.getPassword(), account.getEmail());
        } catch (Exception e) {
            throw new InternalServerException("Data error updating account");
        }
    }

    @Override
    public void deleteAccount(String username) {
        Optional<Account> accountFind = accountRepository.findById(username);

        if(accountFind.isEmpty()) {
            throw new NotFoundException("Not found account has username " + username);
        }

        try {
            accountRepository.deleteById(username);
        } catch (Exception e) {
            throw new InternalServerException("Data error deleting account");
        }
    }
}
