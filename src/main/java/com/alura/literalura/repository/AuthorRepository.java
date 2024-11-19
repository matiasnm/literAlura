package com.alura.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alura.literalura.model.Author;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    Optional<Author> findByNameContainingIgnoreCase(String name);

    @Query("SELECT a FROM Author a WHERE :year > a.birth_year AND :year < a.death_year")
    List<Author> findByLivingInYear(@Param("year") Integer year);
}