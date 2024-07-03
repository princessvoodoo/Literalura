package com.example.demo.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestUtil {

    private RequestUtil() {
    }

    public static String doGetRequest(String endpoint) {
        //replace whitespace with %20 after ? in the URL
        int index = endpoint.indexOf("?") + 1;
        String before = endpoint.substring(0, index);
        String after = endpoint.substring(index);
        after = after.replaceAll("\\s", "%20");
        endpoint = before + after;
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response code: " + response.statusCode());
            if (response.statusCode() >= 300 && response.statusCode() < 400) {
                String redirectUrl = response.headers().firstValue("Location").orElse("");
                if (!redirectUrl.isEmpty()) {
                    return doGetRequest(redirectUrl);
                }
            }
            return response.body();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "";
        }
    }
    
}
