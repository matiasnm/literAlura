package com.alura.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiConsumer {

    protected String getApiRequest(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();
        try {
            HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
            String jsonBody = response.body();
            log.info("Api Consumer: API connection established.");
            return jsonBody;
        } catch (IOException | InterruptedException | IllegalArgumentException | SecurityException ex) {
            log.info("Api Consumer: Exception=" + ex);
            return null;
        }
    }
    
}
