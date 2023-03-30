package com.example.ontapbuoi3.Controller;

import com.example.ontapbuoi3.Model.AccountLogin;
import com.example.ontapbuoi3.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    public AccountService accountService;

    @GetMapping(value = {"/login", "/"})
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("/signup")
    public ModelAndView signup() {
        return new ModelAndView("signup");
    }

    @GetMapping("/api/accounts")
    public ResponseEntity<?> getAPI(Model model) {
        List<AccountLogin> listAcc = accountService.getListAcc();

        return ResponseEntity.ok().body(listAcc);
    }

    @GetMapping("/store")
    public ModelAndView getListAcc(Model model) {
        List<AccountLogin> listAcc = accountService.getListAcc();
        model.addAttribute("listAcc", listAcc);
        return new ModelAndView("store");
    }

    @PostMapping("/login")
    public ModelAndView loginRedirect(@ModelAttribute AccountLogin accountLogin, Model model) {
        List<AccountLogin> listAcc = accountService.getListAcc();
        for (AccountLogin acc : listAcc) {
            if(acc.equals(accountLogin)) {
                return new ModelAndView("redirect:store");
            }
        }
        model.addAttribute("wrong", "x Đăng nhập thất bại: sai tên tài khoản hoặc mật khẩu");
        return new ModelAndView("login");
    }

    @PostMapping("signup")
    public ModelAndView signupRedirect(@RequestParam String username, String password, String password2, String email, Model model) {
        if(password.compareTo(password2) != 0) {
            model.addAttribute("wrong", "x Đăng ký thất bại: mật khẩu không khớp");
            return new ModelAndView("signup");
        } else {
            AccountLogin checkAcc = new AccountLogin(username, password, email);
            if(!accountService.addAccount(checkAcc)) {
                model.addAttribute("wrong", "x Đăng ký thất bại: tên tài khoản hoặc email đã tồn tại");
                return new ModelAndView("signup");
            } else {
                return new ModelAndView("login");
            }
        }
    }



}
