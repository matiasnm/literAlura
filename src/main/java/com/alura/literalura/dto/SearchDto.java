package com.alura.literalura.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"next","previous"})

public record SearchDto(
    Integer count,
    List<BookDto> results
) {}
