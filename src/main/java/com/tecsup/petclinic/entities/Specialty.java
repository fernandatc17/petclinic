package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;

@Entity(name = "specialties")
@Data
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String office;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "h_open")
    private String hOpen;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "h_close")
    private String hClose;

    public Specialty() {
    }

    public Specialty(Integer id, String name, String office, String hOpen, String hClose) {
        this.id = id;
        this.name = name;
        this.office = office;
        this.hOpen = hOpen;
        this.hClose = hClose;
    }

    public Specialty(String name, String office, String hOpen, String hClose) {
        this.name = name;
        this.office = office;
        this.hOpen = hOpen;
        this.hClose = hClose;
    }

    public Specialty(String speName) {
        this.name = speName;
    }
}
