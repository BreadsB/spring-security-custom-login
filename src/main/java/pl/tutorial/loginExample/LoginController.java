package pl.tutorial.loginExample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/app/welcome")
    public String getWelcomePage() {
        return "app/welcome";
    }

    @GetMapping("/signin")
    public String getSignInPage() {
        return "signin";
    }
}