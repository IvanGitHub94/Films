package com.jpcsaturrday.springlibraryproject.library.config;

import com.jpcsaturrday.springlibraryproject.library.service.auth.CustomAuthenticationSuccessHandler;
import com.jpcsaturrday.springlibraryproject.library.service.userDetails.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;

import static com.jpcsaturrday.springlibraryproject.library.constants.UserFRolesConstants.ADMIN;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    CustomUserDetailsService customUserDetailsService;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private final List<String> RESOURCES_WHITE_LIST = List.of(
            "/resources/**",
            "/static/**",
            "/css/**",
            "/js/**",
            "/swagger-ui/**",
            "/"
    );
    private final List<String> FILMS_WHITE_LIST = List.of("/films");

    private final List<String> FILMS_PERMISSIONS_LIST = List.of(
            "/films/add",
            "/films/update",
            "/films/delete",
            "/directors/add"
    );

    private final List<String> FILMS_ADMIN_LIST = List.of(
            "/films/add-director"
    );

    private final List<String> USER_WHITE_LIST = List.of(
            "/login",
            "/users/registration",
            "/users/remember-password/"
    );


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().disable()
                .csrf().disable()
                //Настройка http-запросов - кому / куда, можно / нельзя
                .authorizeHttpRequests((request) -> request
                        .requestMatchers(RESOURCES_WHITE_LIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(FILMS_WHITE_LIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(USER_WHITE_LIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(FILMS_PERMISSIONS_LIST.toArray(String[]::new)).hasAnyRole(ADMIN)
                        .requestMatchers(FILMS_ADMIN_LIST.toArray(String[]::new)).hasAnyRole(ADMIN)
                        .anyRequest().authenticated()
                )
                //Настройки для входа в систему и выхода
                .formLogin((form) -> form
                        .loginPage("/login")
                        //перенаправление после успешного логина
                        .successHandler(authenticationSuccessHandler)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                );

        return httpSecurity.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

}

