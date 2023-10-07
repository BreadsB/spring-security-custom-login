package pl.tutorial.loginExample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/welcome")
    public String getWelcomePage() {
        return "welcome";
    }

    @GetMapping("/login")
    public String getSignInPage() {
        return "login";
    }
}