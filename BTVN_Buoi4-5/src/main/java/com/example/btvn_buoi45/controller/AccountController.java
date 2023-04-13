package com.example.btvn_buoi45.controller;

import com.example.btvn_buoi45.service.AccountService;
import com.example.btvn_buoi45.entity.Account;
import com.example.btvn_buoi45.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @GetMapping(value = {"/login", "/"})
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("/signup")
    public ModelAndView signup() {
        return new ModelAndView("signup");
    }

    @GetMapping("/store")
    public ModelAndView store(Model model) {
        model.addAttribute("list",accountRepository.findAll());
        return new ModelAndView("store");
    }

    @GetMapping("/api/accounts")
    public ResponseEntity<?> getApi() {
        return ResponseEntity.ok().body(accountRepository.findAll());
    }

    @PostMapping("/login")
    public ModelAndView loginRedirect(@ModelAttribute Account accountLogin, Model model) {
        List<Account> list = accountService.findAll();
        for (Account account : list) {
            if(account.getUsername().equals(accountLogin.getUsername()) && account.getPassword().equals(accountLogin.getPassword())) {
                return new ModelAndView("redirect:/store");
            }
        }
        model.addAttribute("wrong", "x Đăng nhập thất bại: sai tên tài khoản hoặc mật khẩu");
        return new ModelAndView("login");
    }

    @PostMapping("/signup")
    public ModelAndView signupRedirect(@RequestParam String username, String password, String password2, String email, Model model) {
        if(password.compareTo(password2) != 0) {
            model.addAttribute("wrong", "x Đăng ký thất bại: mật khẩu không khớp");
            return new ModelAndView("signup");
        } else {
            List<Account> list = accountService.findAll();
            for (Account account : list) {
                if(account.getUsername().equals(username)) {
                    model.addAttribute("wrong", "x Đăng ký thất bại: tên tài khoản đã tồn tại");
                    return new ModelAndView("signup");
                }
            }
            accountService.createAccount(new Account(username, password, email));
            return new ModelAndView("login");
        }
    }

    @GetMapping("/delete/{username}")
    public ModelAndView deleteAccount(@PathVariable String username) {
        accountService.deleteAccount(username);
        return new ModelAndView("redirect:/store");
    }

    @GetMapping("/edit/{username}")
    public ModelAndView edit(@PathVariable String username, Model model) {
        Account account = accountService.findAccountByUsername(username);
        model.addAttribute("username", account.getUsername());
        model.addAttribute("password", account.getPassword());
        model.addAttribute("email", account.getEmail());
        model.addAttribute("dupEmail", account.getEmail());

        return new ModelAndView("edit");
    }

    @PostMapping("/edit")
    public ModelAndView editAccount(@ModelAttribute Account account) {
        accountService.updateAccount(account);
        return new ModelAndView("redirect:/store");
    }

    @GetMapping("/search")
    public ModelAndView searchAccount(@RequestParam(value = "searchUsername") String username, Model model) {
        Account account = accountService.findAccountByUsername(username);
        List<Account> list = new ArrayList<>();
        list.add(account);
        model.addAttribute("list", list);

        return new ModelAndView("store");
    }
}
