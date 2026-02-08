package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UrlRepository extends JpaRepository<Url, Long> {

    Url findByShortCode(String shortCode);
    boolean existsByShortCode(String shortCode);
    Url findByOriginalUrl(String originalUrl);

    List<Url> findByUser(User user);
}
