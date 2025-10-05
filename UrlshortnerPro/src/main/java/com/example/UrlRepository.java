package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByShortCode(String shortCode);
    boolean existsByShortCode(String shortCode);
    Url findByOriginalUrl(String originalUrl);
    List<Url> findAll(); // Make sure this exists
}