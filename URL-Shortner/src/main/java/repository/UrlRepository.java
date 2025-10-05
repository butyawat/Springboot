package repository;



import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortKey(String shortKey);
    Optional<Url> findByOriginalUrl(String originalUrl);
    
    @Modifying
    @Query("UPDATE Url u SET u.clickCount = u.clickCount + 1, u.lastAccessed = :now WHERE u.id = :id")
    void incrementClickCount(Long id, LocalDateTime now);
    
    @Query("SELECT u FROM Url u WHERE u.shortKey = :shortKey AND (u.expiresAt IS NULL OR u.expiresAt > :now)")
    Optional<Url> findActiveByShortKey(String shortKey, LocalDateTime now);
}