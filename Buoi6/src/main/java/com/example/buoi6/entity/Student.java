package com.example.buoi6.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @JsonManagedReference
    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Subject> subjects;

    @JsonManagedReference
    @OneToOne(mappedBy = "student", cascade = CascadeType.REMOVE)
    private Phone phone;

    public Student(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
