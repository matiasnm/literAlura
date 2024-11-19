package com.alura.literalura.dto;

import java.util.List;

import com.alura.literalura.enums.Languages;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"formats","download_count","copyright", "translators", "media_type"})
public record BookDto(
    String detail,
    @JsonAlias("id") Long apiId,
    String title,
    List<String> subjects,
    List<AuthorDto> authors,
    List<String> bookshelves,
    List<Languages> languages
) {}