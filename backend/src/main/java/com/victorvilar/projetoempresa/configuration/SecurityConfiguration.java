package com.victorvilar.projetoempresa.configuration;

import com.victorvilar.projetoempresa.configuration.filters.CsrfCookieSessionFilter;
import com.victorvilar.projetoempresa.configuration.filters.JwtTokenGeneratorFilter;
import com.victorvilar.projetoempresa.configuration.filters.JwtTokenValidatorFilter;
import com.victorvilar.projetoempresa.services.JwtService;
import io.jsonwebtoken.Jwt;
import jakarta.persistence.Basic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private JwtTokenValidatorFilter jwtTokenValidatorFilter;

    @Autowired
    private JwtTokenGeneratorFilter jwtTokenGeneratorFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                //tell to spring that it has the responsability to save the authentication inside SecurityHolder
                .securityContext( security ->{
                    security.requireExplicitSave(false);
                })

                //tell to spring to generate JSessionId only when required
                .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                )
                //cors configuration
                .cors(cors ->cors
                    .configurationSource(new CorsConfiguration())
                )

                //csrf configuration
                .csrf(csrf -> csrf
                        //include de csrf token as attribute of response
                        //.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                        //end points that not have a csrf protection
                        //.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2/**"))
                        //repository of csrf tokens
                        //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .disable()
                )

                //validate the token before the http response
                .addFilterBefore(this.jwtTokenValidatorFilter,BasicAuthenticationFilter.class)
                //send a csrf token to request after an authentication
                //.addFilterAfter(new CsrfCookieSessionFilter(), BasicAuthenticationFilter.class)
                //generates a new jwt token after an authentication
                .addFilterAfter(this.jwtTokenGeneratorFilter,BasicAuthenticationFilter.class)

                //end points configuration and roles
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers(antMatcher( "/h2/**")).permitAll()
                    .requestMatchers(HttpMethod.POST,"/v1/login/**").permitAll()
                    .anyRequest().authenticated()

                )

                //configuration to h2 database
                .headers(header-> header
                                .frameOptions(frame -> frame.disable())
                )

                //default login page configuration
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())

                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/logout.done").deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                );


        return http.build();
    }


}
