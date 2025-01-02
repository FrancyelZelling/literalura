package com.zelling.literAlura.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Api {
    public String getData(String address){
        HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .build();

        HttpResponse<String> response = null;

        try{
           response = client.send(request, HttpResponse.BodyHandlers.ofString());
           return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to make a request, error: " + e.getLocalizedMessage());
        }

    }
}
