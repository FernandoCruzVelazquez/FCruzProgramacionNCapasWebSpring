package com.digis01.FCruzProgramacionNCapasWebSpring.Configuration;

import com.digis01.FCruzProgramacionNCapasWebSpring.Service.UserDetailJPA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserDetailJPA userDetailJPA;

    public SecurityConfiguration(UserDetailJPA userDetailJPA) {
        this.userDetailJPA = userDetailJPA;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

            .authenticationProvider(authenticationProvider())

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/login").permitAll()
                .requestMatchers("/Usuario/**").hasAnyRole("Administrador", "Empleado", "Alumno", "Profesor")
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/Usuario", true)

                .failureHandler((request, response, exception) -> {

                    if (exception instanceof DisabledException) {
                        response.sendRedirect("/login?disabled=true");

                    } else if (exception instanceof UsernameNotFoundException) {
                        response.sendRedirect("/login?userNotFound=true");

                    } else if (exception instanceof BadCredentialsException) {
                        response.sendRedirect("/login?badCredentials=true");

                    } else {
                        response.sendRedirect("/login?error=true");
                    }
                })

                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailJPA);

        provider.setHideUserNotFoundExceptions(false);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }
}