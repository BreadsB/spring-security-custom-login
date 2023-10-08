package pl.tutorial.loginExample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:security-config.properties")
public class SecurityConfig {

    @Value("${security.login.user}")
    private String userLogin;
    @Value("${security.password.user}")
    private String userPassword;
    @Value("${security.login.admin}")
    private String adminLogin;
    @Value("${security.password.admin}")
    private String adminPassword;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User
                .withUsername(userLogin)
                .password(passwordEncoder().encode(userPassword)) // must encode, otherwise won't work
                .roles("USER")
                .build();

        UserDetails admin = User
                .withUsername(adminLogin)
                .password(passwordEncoder().encode(adminPassword))
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/signin")
                        .loginProcessingUrl("/login-action") // must start with slash '/' & must be the same in HTML form action
                        .defaultSuccessUrl("/app/welcome", true) // must have 'true' parameter, otherwise redirect to localhost:8080/?continue
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/perform_logout")
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }
}
