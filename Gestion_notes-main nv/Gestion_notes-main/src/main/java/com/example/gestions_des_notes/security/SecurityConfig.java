package com.example.gestions_des_notes.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Seul l'admin peut accéder à /admin
                        .requestMatchers("/students/**").hasAnyRole("USER", "ADMIN") // Les utilisateurs et admin peuvent accéder à /students
                        .anyRequest().authenticated() // Toutes les autres requêtes nécessitent une authentification
                )
                .formLogin((form) -> form
                        .loginPage("/login") // Page de connexion personnalisée
                        .permitAll() // Autoriser l'accès à la page de connexion pour tous
                )
                .logout((logout) -> logout.permitAll()); // Autoriser la déconnexion pour tous
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}


