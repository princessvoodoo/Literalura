package com.example.demo.domain.formats;

public class FormatFactory {
    
    public static Format createFormat(String name, String url) {
        return Format.builder()
            .name(name)
            .url(url)
            .build();
    }

    
}
