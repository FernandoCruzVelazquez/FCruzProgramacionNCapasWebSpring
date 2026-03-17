package com.digis01.FCruzProgramacionNCapasWebSpring.Configuration;

import com.digis01.FCruzProgramacionNCapasWebSpring.Service.UserDetailJPA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
            // 1. Deshabilitar CSRF temporalmente si tienes problemas (opcional para pruebas)
            // .csrf(csrf -> csrf.disable()) 

            .authorizeHttpRequests(auth -> auth
                // Recursos estáticos y página de login SIEMPRE permitidos
                .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/login").permitAll()

                // Roles específicos
                .requestMatchers("/Usuario/**").hasAnyRole("Administrador", "Empleado", "Alumno", "Profesor")

                // El resto requiere login
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")                
                .loginProcessingUrl("/login") // El POST del formulario
                .defaultSuccessUrl("/Usuario", true)  
                .failureUrl("/login?error=true") 
                .permitAll() // Esto es vital: permite acceso a la página y al POST de login
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )

            // Elimina el entryPoint manual para que Spring use el de por defecto de formLogin
            .userDetailsService(userDetailJPA);

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}