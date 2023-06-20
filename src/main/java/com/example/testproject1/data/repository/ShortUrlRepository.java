package com.example.testproject1.data.repository;

import com.example.testproject1.data.entity.ShortUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrlEntity, Long> {

    ShortUrlEntity findByUrl(String url);
    ShortUrlEntity findByOrgUrl(String originalUrl);

}
