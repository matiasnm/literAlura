package com.alura.literalura.model;

import java.util.List;

import com.alura.literalura.dto.AuthorDto;

import jakarta.persistence.*;

@Entity
@Table(name = "authors")
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer birth_year;

    private Integer death_year;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "authors", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Book> books;

    public Author() {}
    
    public Author(Long id, Integer birth_year, Integer death_year, String name) {
        this.id = id;
        this.birth_year = birth_year;
        this.death_year = death_year;
        this.name = name;
    }

    public Author(AuthorDto personDto) {
        this.birth_year = personDto.birth_year();
        this.death_year = personDto.death_year();
        this.name = personDto.name().replace("+", " ");;
    }

    public Long getId() {
        return id;
    }

    public Integer getBirth_year() {
        return birth_year;
    }

    public Integer getDeath_year() {
        return death_year;
    }

    public String getName() {
        return name;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Autor: " + name + ", (" + birth_year + "-" + death_year + ")\n";
    }

}