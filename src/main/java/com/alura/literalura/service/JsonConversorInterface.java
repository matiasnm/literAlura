package com.alura.literalura.service;

public interface JsonConversorInterface {
    <T> T getJson(String json, Class<T> clase);
}
