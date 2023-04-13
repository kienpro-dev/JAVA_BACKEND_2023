package com.example.btvn_buoi45.controller;

import com.example.btvn_buoi45.entity.Account;
import com.example.btvn_buoi45.exception.InternalServerException;
import com.example.btvn_buoi45.repository.AccountRepository;
import com.example.btvn_buoi45.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class ApiController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public ResponseEntity<?> getApi() {
        return ResponseEntity.ok().body(accountService.findAll());
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<?> findAccountByUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(accountService.findAccountByUsername(username));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok().body(accountService.createAccount(account));
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<?> deleteAccount(@PathVariable String username) {
        accountService.deleteAccount(username);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update/{username}")
    public ResponseEntity<?> updateAccountPatch(@PathVariable String username, @RequestBody Account account) {
        Account oldAccount = accountService.findAccountByUsername(username);
        if(account.getPassword() !=  null) {
            oldAccount.setPassword(account.getPassword());
        }
        if(account.getEmail() != null) {
            oldAccount.setEmail(account.getEmail());
        }
        accountService.updateAccount(oldAccount);
        return ResponseEntity.ok().body(oldAccount);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<?> updateAccountPut(@PathVariable String username, @RequestBody Account account) {
        Account oldAccount = accountService.findAccountByUsername(username);
        if(account.getPassword() == null || account.getEmail() == null) {
            throw new InternalServerException("Data error updating account");
        }
        oldAccount.setPassword(account.getPassword());
        oldAccount.setEmail(account.getEmail());
        accountService.updateAccount(oldAccount);
        return ResponseEntity.ok().body(oldAccount);
    }
}
