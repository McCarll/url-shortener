package ru.mccarl.url.shortener.storage;

import org.springframework.data.repository.CrudRepository;
import ru.mccarl.url.shortener.model.UrlModel;

import java.util.List;

public interface UrlRepository extends CrudRepository<UrlModel, Long> {
    List<UrlModel> findByUrlKey(String key);
    List<UrlModel> findByShortUrl(String key);
}
