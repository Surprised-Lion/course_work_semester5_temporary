package org.example.security;


import org.example.exceptions.SecurityConfigurationException;
import org.example.exceptions.exceptionHandlers.CustomAccessDeniedHandler;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        try {
            userService.setAuthenticationManager(customAuthenticationManager());
            http.authorizeRequests()
                    .antMatchers("/sign_up", "/login", "/user/*", "/user/{userId}/*", "/author/{authorId}",
                            "/{authorId}/poems", "/author/all", "/poem/all", "/poem/*", "/{poemId}/author", "/author/{authorId}/poems", "/poem/{poemId}/comment").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic()
                    .and()
                    .logout().permitAll()
                    .and()
                    .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler())
                    .and().sessionManagement().disable();
            http.csrf().disable();
        } catch (Exception exception) {
            throw new SecurityConfigurationException(exception.getMessage());
        }
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        try {
            auth.userDetailsService(userService).passwordEncoder(encoder);
        } catch (Exception exception) {
            throw new SecurityConfigurationException(exception.getMessage());
        }
    }


    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
}