package ru.mccarl.url.shortener.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.mccarl.url.shortener.service.UrlShortenerService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/url")
public class UrlShortenerRestApi {
    private UrlShortenerService urlShortenerService;

    public UrlShortenerRestApi(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping
    public ResponseEntity shortUrl(HttpServletRequest request,  @RequestBody String url) {
        return ResponseEntity.ok(urlShortenerService.createShortUrl(request, url));
    }
    @GetMapping("/{key}")
    public RedirectView userShortUrl(HttpServletRequest request, @PathVariable String key){
        String url = urlShortenerService.useShortUrl(request, key);
        RedirectView redirectView = new RedirectView(url);
        return redirectView;
    }
}
