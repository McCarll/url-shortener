package ru.mccarl.url.shortener.service;

import javax.servlet.http.HttpServletRequest;

public interface UrlShortenerService {
    String createShortUrl(HttpServletRequest request, String key);
    String useShortUrl(HttpServletRequest request, String key);
}
