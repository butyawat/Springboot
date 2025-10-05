package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class UrlService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 6;

    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(String originalUrl) {
        // Check if URL already exists
        Url existingUrl = urlRepository.findByOriginalUrl(originalUrl);
        if (existingUrl != null) {
            return existingUrl.getShortCode();
        }
        
        String shortCode;
        do {
            shortCode = generateShortCode();
        } while (urlRepository.existsByShortCode(shortCode));

        Url url = new Url(originalUrl, shortCode);
        urlRepository.save(url);
        return shortCode;
    }

    public String getOriginalUrl(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode);
        if (url != null) {
            url.incrementClickCount();
            urlRepository.save(url);
            return url.getOriginalUrl();
        }
        return null;
    }
    
    
    public List<Url> getAllUrls() {
        return urlRepository.findAll();
    }

    private String generateShortCode() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}