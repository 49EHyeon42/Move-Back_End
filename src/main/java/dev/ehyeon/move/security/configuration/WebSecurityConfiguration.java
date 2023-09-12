package dev.ehyeon.move.security.configuration;

import dev.ehyeon.move.security.local.jwt.JwtAuthenticationFilter;
import dev.ehyeon.move.security.local.signin.SignInFilter;
import dev.ehyeon.move.security.local.signup.SignUpFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final SignInFilter signInFilter;
    private final SignUpFilter signUpFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Authentication
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(signInFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(signUpFilter, SignInFilter.class)
                .addFilterAfter(jwtAuthenticationFilter, SignUpFilter.class);

        // Authorization
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated();
    }
}
