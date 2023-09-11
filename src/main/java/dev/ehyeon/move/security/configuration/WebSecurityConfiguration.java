package dev.ehyeon.move.security.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ehyeon.move.security.local.*;
import dev.ehyeon.move.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
    private final SignInAuthenticationProvider signInAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final SignService signService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(signInAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Authentication
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(getEmailPasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(getSignUpFilter(), SignInAuthenticationFilter.class)
                .addFilterAfter(getJwtAuthenticationFilter(), SignUpFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint);

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

    @Bean
    public SignInAuthenticationFilter getEmailPasswordAuthenticationFilter() throws Exception {
        SignInAuthenticationFilter signInAuthenticationFilter = new SignInAuthenticationFilter(
                new AntPathRequestMatcher("/api/signin", HttpMethod.POST.name()), authenticationManager(), objectMapper);

        signInAuthenticationFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(new SignInResponse((String) authentication.getPrincipal())));
        });

        return signInAuthenticationFilter;
    }

    @Bean
    public SignUpFilter getSignUpFilter() {
        return new SignUpFilter(new AntPathRequestMatcher("/api/signup", HttpMethod.POST.name()), objectMapper, signService);
    }

    @Bean
    public JwtAuthenticationFilter getJwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(new AntPathRequestMatcher("/api/**"), authenticationManager(), objectMapper);
    }
}
