package com.Eventify.Eventify.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")          // صفحة login ديالك
                        .defaultSuccessUrl("/home", true) // بعد login غادي يوجّه home
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}
