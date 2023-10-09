package pl.tutorial.loginExample;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/app/user/welcome")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String getWelcomePage() {
        return "app/user/userPage";
    }

    @GetMapping("/signin")
    public String getSignInPage() {
        return "signin";
    }

    @GetMapping("/app/admin/welcome")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminWelcomePage() {
        return "app/admin/adminPage";
    }
}
