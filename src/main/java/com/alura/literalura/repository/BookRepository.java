package com.alura.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alura.literalura.enums.Languages;
import com.alura.literalura.model.Book;
import com.alura.literalura.model.Author;


public interface BookRepository extends JpaRepository<Book,Long> {

    Optional<Book> findByTitleContainsIgnoreCase(String title);

    @Query("SELECT b FROM Book b WHERE :lang MEMBER OF b.languages")
    List<Book> findByLanguages(@Param("lang") Languages lang);

    List<Book> findByAuthors(Author author);

    @Query("SELECT DISTINCT lang FROM Book b JOIN b.languages lang")
    List<Languages> listLanguages();

}