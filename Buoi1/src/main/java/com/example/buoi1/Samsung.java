package com.example.buoi1;

import org.springframework.stereotype.Component;

@Component("Samsung")
public class Samsung implements Phone{
    @Override
    public void using() {
        System.out.println("Using samsung");
    }
}
