package com.example.buoi2.Controller;

import com.example.buoi2.Student;
import com.example.buoi2.XeWave;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController
{
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("string", "Hello world");
        model.addAttribute("student", new Student("Kien", 20));
        List<Student> ds= new ArrayList<>();
        ds.add(new Student("Phuong", 20));
        ds.add(new Student("Tuan", 20));
        ds.add(new Student("Quan", 20));
        model.addAttribute("list", ds);

//        ModelAndView mav = new ModelAndView("index");
//        mav.addObject("list", ds);
//        mav.addObject("string", "Hello world");
        return "index";
    }
    @RequestMapping("/about")
    public String about(@RequestParam("name") String name,@RequestParam("age") String age, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "about";
    }

    @RequestMapping("/test")
    public String test(@ModelAttribute Student student, Model model){
        model.addAttribute("student", student);
        return "test";
    }


}
