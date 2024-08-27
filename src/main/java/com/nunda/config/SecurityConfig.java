package com.nunda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final AuthenticationProvider customAuthProvider;

    public SecurityConfig(AuthenticationProvider customAuthProvider){
        this.customAuthProvider =  customAuthProvider;
    }

    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {

        http
                .authenticationProvider(customAuthProvider)
                .authorizeHttpRequests((authorize) -> authorize

                        .requestMatchers("/features").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/register").permitAll()
                        .anyRequest().authenticated()


                )
                .formLogin(form -> form
                        .loginPage("/login")  // Custom login page
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/home", true)  // Redirect to /home after successful login
                        .failureUrl("/login?error=true")   // Redirect to /login with error message on failure
                        .permitAll()  // Allow everyone to see the login page
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // Form action URL
                        .logoutSuccessUrl("/?logout=true")  // Redirect after logout
                        .invalidateHttpSession(true)  // Invalidate session
                        .deleteCookies("JSESSIONID")  // Delete cookies (optional)
                        .permitAll()
                );


        // ...

        return http.build();
    }
}
