package ru.mccarl.url.shortener.service.impl;

import org.springframework.stereotype.Component;
import ru.mccarl.url.shortener.model.UrlModel;
import ru.mccarl.url.shortener.service.UrlShortenerService;
import ru.mccarl.url.shortener.storage.UrlRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UrlShortenerServiceImpl implements UrlShortenerService {

    private UrlRepository storageService;

    public UrlShortenerServiceImpl(UrlRepository storageService) {
        this.storageService = storageService;
    }

    @Override
    public String createShortUrl(HttpServletRequest request, String key) {
        List<UrlModel> urls = checkUrlExist(key);
        String baseUrl = request.getRequestURL().toString();
        if (urls.isEmpty()) {
            return create(baseUrl, key).getShortUrl();
        }
        return urls.get(0).getShortUrl();
    }

    private UrlModel create(String baseUrl , String key) {

        UrlModel urlModel = new UrlModel();
        urlModel.setUrlKey(key);
        urlModel.setShortUrl(key);
        storageService.save(urlModel);
        urlModel.setShortUrl(String.format("%s/%s", baseUrl,  urlModel.getUrlId()));
        storageService.save(urlModel);
        return urlModel;
    }

    @Override
    public String useShortUrl(HttpServletRequest request, String key) {
        String baseUrl = request.getRequestURL().toString();
        List<UrlModel> urlModels = Optional.ofNullable(storageService.findByShortUrl(baseUrl)).orElse(Collections.EMPTY_LIST);
        if (urlModels.isEmpty()) {
            throw new IllegalArgumentException("Cant find short url");
        } else {
            return urlModels.get(0).getUrlKey();
        }
    }

    private List<UrlModel> checkUrlExist(String key) {
        try {
            return storageService.findByUrlKey(key);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

}
