package com.example;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(User user) {
        userRepository.save(user);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(String userId, String password,
                        HttpSession session, Model model) {

        User user = userRepository.findByUserId(userId);

        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid UserId or Password");
            return "login";
        }

        session.setAttribute("loggedInUser", user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
