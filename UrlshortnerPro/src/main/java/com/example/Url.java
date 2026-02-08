package com.example;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalUrl;
    private String shortCode;
    private LocalDateTime createdAt;
    private int clickCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Url() {
        this.clickCount = 0;
        this.createdAt = LocalDateTime.now();
    }

    public Url(String originalUrl, String shortCode) {
        this();
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
    }

    public Long getId() { return id; }
    public String getOriginalUrl() { return originalUrl; }
    public String getShortCode() { return shortCode; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public int getClickCount() { return clickCount; }
    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public void incrementClickCount() {
        this.clickCount++;
    }
}
