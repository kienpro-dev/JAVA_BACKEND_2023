package com.example.buoi1;

import org.springframework.stereotype.Component;

@Component("Iphone")
public class Iphone implements Phone {
    @Override
    public void using() {
        System.out.println("Using iphone");
    }
}
