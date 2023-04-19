package com.example.buoi6.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String brand;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Phone(String name, String brand, Student student) {
        this.name = name;
        this.brand = brand;
        this.student = student;
    }
}
