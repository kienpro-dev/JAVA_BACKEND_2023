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
//        Optional<Account> accountFind = accountRepository.findByUsername(username);
//
//        if(accountFind.isEmpty()) {
//            throw new NotFoundException("Not found account has username " + username);
//        }
//
//        return accountFind.get();
        return null;
    }

    @Override
    public void createAccount(Account account) {
        try {
            accountRepository.save(account);
        } catch (Exception e) {
            throw new InternalServerException("Data error saving account");
        }
    }

    @Override
    public void updateAccount(Account account) {
//        Optional<Account> accountFind = accountRepository.findByUsername(account.getUsername());
//
//        if(accountFind.isEmpty()) {
//            throw new NotFoundException("Not found account has username " + account.getUsername());
//        }
//
//        accountFind.get().setUsername(account.getUsername());
//        accountFind.get().setPassword(account.getPassword());
//        accountFind.get().setEmail(account.getEmail());
//        accountRepository.save(accountFind.get());

    }

    @Override
    public void deleteAccount(String username) {
//        Account accountFind = accountRepository.findByUsername(username);
//
//        if(accountFind == null) {
//            throw new NotFoundException("Not found account has username " + username);
//        }

//        Long id = null;
//        List<Account> list = findAll();
//        for (Account acc : list) {
//            if(acc.getUsername().equals(username)) {
//                id = acc.getId();
//                break;
//            }
//        }
//
//        accountRepository.deleteById(id);
        accountRepository.deleteAccountByUsername(username);
    }
}
