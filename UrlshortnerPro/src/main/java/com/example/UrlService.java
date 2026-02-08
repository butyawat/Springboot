package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UrlService {

    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(String originalUrl, User user) {

        String shortCode;
        do {
            shortCode = generateCode();
        } while (urlRepository.existsByShortCode(shortCode));

        Url url = new Url(originalUrl, shortCode);
        url.setUser(user);

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

    public List<Url> getUrlsByUser(User user) {
        return urlRepository.findByUser(user);
    }

    public void deleteUrl(Long id, User user) {
        Url url = urlRepository.findById(id).orElse(null);
        if (url != null && url.getUser().getId().equals(user.getId())) {
            urlRepository.deleteById(id);
        }
    }

    private String generateCode() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(CHARS.charAt(r.nextInt(CHARS.length())));
        }
        return sb.toString();
    }
}
