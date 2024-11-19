package com.alura.literalura.model;

import java.util.List;

import com.alura.literalura.dto.BookDto;
import com.alura.literalura.enums.Languages;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long apiId;

    @Column(unique = true)
    private String title;

    private List<String> subjects;

    @ManyToOne
    private Author authors;

    private List<String> bookshelves;
    
    @ElementCollection(fetch = FetchType.EAGER, targetClass = Languages.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "book_languages", joinColumns = @JoinColumn(name = "book_id"))
    private List<Languages> languages;

    public Book() {}

    public Book(Long id, Long apiId, String title, List<String> subjects, Author authors, List<String> bookshelves,
            List<Languages> languages) {
        this.id = id;
        this.apiId = apiId;
        this.title = title;
        this.subjects = subjects;
        this.authors = authors;
        this.bookshelves = bookshelves;
        this.languages = languages;
    }

    public Book(BookDto bookDto) {
        this.apiId = bookDto.apiId();
        this.title = bookDto.title().replace("+", " ");;
        this.subjects = bookDto.subjects().stream().map(s -> s.replace("+", " ")).toList();
        this.bookshelves = bookDto.bookshelves().stream().map(s -> s.replace("+", " ")).toList();
        this.languages = bookDto.languages();
    }

    public Long getId() {
        return id;
    }

    public Long getGutenbergId() {
        return apiId;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public Author getAuthors() {
        return authors;
    }

    public List<String> getBookshelves() {
        return bookshelves;
    }

    public List<Languages> getLanguages() {
        return languages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public void setAuthors(Author authors) {
        this.authors = authors;
    }

    public void setBookshelves(List<String> bookshelves) {
        this.bookshelves = bookshelves;
    }

    public void setLanguages(List<Languages> languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "Título: " + title + "\nAuthor: " + authors.getName() + "\nIdiomas: " + languages + "\n";
    }

}