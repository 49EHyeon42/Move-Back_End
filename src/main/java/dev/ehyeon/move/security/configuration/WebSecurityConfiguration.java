package dev.ehyeon.move.security.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ehyeon.move.security.local.JwtAuthenticationFilter;
import dev.ehyeon.move.security.local.SignInFilter;
import dev.ehyeon.move.security.local.SignUpFilter;
import dev.ehyeon.move.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final ObjectMapper objectMapper;
    private final SignService signService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Authentication
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(getSignInFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(getSignUpFilter(), SignInFilter.class)
                .addFilterAfter(getJwtAuthenticationFilter(), SignUpFilter.class);

        // Authorization
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    public SignInFilter getSignInFilter() {
        return new SignInFilter(new AntPathRequestMatcher("/api/signin", HttpMethod.POST.name()), objectMapper, signService);
    }

    @Bean
    public SignUpFilter getSignUpFilter() {
        return new SignUpFilter(new AntPathRequestMatcher("/api/signup", HttpMethod.POST.name()), objectMapper, signService);
    }

    public JwtAuthenticationFilter getJwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(new AntPathRequestMatcher("/api/**"), signService);
    }
}
