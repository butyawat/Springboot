package com.example;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;

@Controller
public class URLController {

    @Autowired
    private UrlService urlService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/shorten")
    public String shorten(String originalUrl, Model model, HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        if (!originalUrl.startsWith("http")) {
            originalUrl = "http://" + originalUrl;
        }

        String code = urlService.shortenUrl(originalUrl, user);
        model.addAttribute("shortUrl", "http://localhost:8080/u/" + code);
        model.addAttribute("originalUrl", originalUrl);

        return "result";
    }
    
    @GetMapping("/u/{shortCode}")
    public RedirectView redirect(@PathVariable String shortCode) {
        String url = urlService.getOriginalUrl(shortCode);
        return new RedirectView(url != null ? url : "/");
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        List<Url> urls = urlService.getUrlsByUser(user);

        int totalClicks = 0;
        int maxClicks = 0;
        for (Url u : urls) {
            totalClicks += u.getClickCount();
            if (u.getClickCount() > maxClicks) {
                maxClicks = u.getClickCount();
            }
        }

        model.addAttribute("urls", urls);
        model.addAttribute("totalUrls", urls.size());
        model.addAttribute("totalClicks", totalClicks);
        model.addAttribute("maxClicks", maxClicks);

        return "dashboard";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            urlService.deleteUrl(id, user);
        }

        return "redirect:/dashboard";
    }
}
