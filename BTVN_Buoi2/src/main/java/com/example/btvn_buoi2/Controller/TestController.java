package com.example.btvn_buoi2.Controller;

import com.example.btvn_buoi2.Model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {
    @RequestMapping("/home")
    public String index(Model model) {
        return "index";
    }

    @PostMapping("/home")
    public String redirect(@RequestParam("username") String username, @RequestParam("password") String password, Model model)
    {
        if(username.equals("kien2372003") && password.equals("&@kiE_n?")) {
            return "redirect:about";
        } else {
            model.addAttribute("wrong", "Sai thông tin tài khoản hoặc mật khẩu");
            return "index";
        }
    }

    @RequestMapping("/about")
    public String about(Model model) {
        Student[] list = {
                new Student("2021608239","Nguyễn Tiến Kiên", "CNTT", 3.6),
                new Student("2021602712","Hoàng Văn Thụ", "CNTT", 3.3),
                new Student("2021610223","Đỗ Tiến Đạt", "KHMT", 2.6),
                new Student("2021609101","Lương Việt Hoàng", "KHMT", 3.0),
                new Student("2021608992","Hà Quang Bách", "CNTT", 3.1)
        };
        model.addAttribute("List", list);
        return "about";
    }



}
