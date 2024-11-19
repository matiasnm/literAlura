package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorDto(
    Integer birth_year,
    Integer death_year,
    String name
) {}