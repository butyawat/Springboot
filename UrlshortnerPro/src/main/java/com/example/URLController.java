package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;

@Controller
public class URLController {
    @Autowired
    private UrlService urlService;

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @PostMapping("/shorten")
    public String shortenUrl(String originalUrl, Model model) {
        if (originalUrl == null || originalUrl.trim().isEmpty()) {
            model.addAttribute("error", "Please enter a valid URL");
            return "index";
        }
        
        if (!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://")) {
            originalUrl = "http://" + originalUrl;
        }
        
        String shortCode = urlService.shortenUrl(originalUrl);
        String shortUrl = "http://localhost:8080/" + shortCode;
        model.addAttribute("shortUrl", shortUrl);
        model.addAttribute("originalUrl", originalUrl);
        return "result";
    }

    @GetMapping("/{shortCode}")
    public RedirectView redirectToOriginal(@PathVariable String shortCode) {
        String originalUrl = urlService.getOriginalUrl(shortCode);
        if (originalUrl != null) {
            return new RedirectView(originalUrl);
        }
        return new RedirectView("/");
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Url> urls = urlService.getAllUrls();
        model.addAttribute("urls", urls);
        
        // Calculate statistics
        int totalUrls = urls.size();
        int totalClicks = 0;
        int maxClicks = 0;
        
        for (Url url : urls) {
            totalClicks += url.getClickCount();
            if (url.getClickCount() > maxClicks) {
                maxClicks = url.getClickCount();
            }
        }
        
        model.addAttribute("totalUrls", totalUrls);
        model.addAttribute("totalClicks", totalClicks);
        model.addAttribute("maxClicks", maxClicks);
        
        return "dashboard";
    }
}