package com.example.testproject1.data.dao.impl;

import com.example.testproject1.data.dao.ShortUrlDAO;
import com.example.testproject1.data.entity.ShortUrlEntity;
import com.example.testproject1.data.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlDaoImpl implements ShortUrlDAO {

    private final ShortUrlRepository shortUrlRepository;

    @Autowired
    public ShortUrlDaoImpl(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    @Override
    public ShortUrlEntity saveShortUrl(ShortUrlEntity shortUrl) {
        ShortUrlEntity foundShortUrl = shortUrlRepository.save(shortUrl);
        return foundShortUrl;
    }

    @Override
    public ShortUrlEntity getShortUrl(String originalUrl) {
        ShortUrlEntity foundShortUrl = shortUrlRepository.findByOrgUrl(originalUrl);
        return foundShortUrl;
    }

    @Override
    public ShortUrlEntity getOriginalUrl(String shortUrl) {
        ShortUrlEntity foundShortUrl = shortUrlRepository.findByUrl(shortUrl);
        return foundShortUrl;
    }

    @Override
    public ShortUrlEntity updateShortUrl(ShortUrlEntity newShortUrl) {
        ShortUrlEntity foundShortUrl = shortUrlRepository.findByOrgUrl(newShortUrl.getOrgUrl());

        foundShortUrl.setUrl(newShortUrl.getUrl());

        ShortUrlEntity savedShortUrl = shortUrlRepository.save(foundShortUrl);

        return savedShortUrl;
    }

    @Override
    public void deleteByShortUrl(String shortUrl) {
        ShortUrlEntity foundShortUrl = shortUrlRepository.findByUrl(shortUrl);
        shortUrlRepository.delete(foundShortUrl);
    }

    @Override
    public void deleteByOriginalUrl(String originalUrl) {
        ShortUrlEntity foundShortUrl = shortUrlRepository.findByOrgUrl(originalUrl);
        shortUrlRepository.delete(foundShortUrl);
    }
}