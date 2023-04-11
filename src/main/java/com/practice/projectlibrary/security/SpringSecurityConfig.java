package com.practice.projectlibrary.security;


import com.practice.projectlibrary.security.jwt.JwtAuthenticationFilter;
import com.practice.projectlibrary.service.impl.MyUserDetailSeviceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
@RequiredArgsConstructor
public class SpringSecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailSeviceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**", "/api/v1/users/**", "/api/v1/token/**")
                .permitAll()
                .requestMatchers("/swagger-ui/**","/v3/api-docs/**")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/books/**", "/api/v1/categories/**", "/api/v1/roles/**")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/books/**", "/api/v1/categories/**", "/api/v1/roles/**")
                .hasAnyAuthority("admin", "librarian")
                .requestMatchers(HttpMethod.PUT, "/api/v1/books/**", "/api/v1/categories/**", "/api/v1/roles/**")
                .hasAnyAuthority("admin", "librarian")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/books/**", "/api/v1/categories/**", "/api/v1/roles/**")
                .hasAnyAuthority("admin", "librarian")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
