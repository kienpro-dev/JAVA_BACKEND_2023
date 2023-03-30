package com.example.buoi3.Controller;

import com.example.buoi3.Account;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller2 {
    @GetMapping("/")
    public ResponseEntity<Account> index() {
        Account x = new Account("kien2372003", "kien2003");
        HttpHeaders headers = new HttpHeaders();
        headers.set("username", "root");
        return ResponseEntity.ok().headers(headers).body(x);
    }
}