package com.amigoscode.SpringSecuirty.Security;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // ! to use @PreAuthorize

public class SecurityConfig {
        private final PasswordEncoder passwordEncoder; // Configration take place to use BCrypt

        // @Autowired
        public SecurityConfig(PasswordEncoder passwordEncoder) {
                this.passwordEncoder = passwordEncoder;
        }

        // ! Use SecuirtyFilterChain to config requests
        // ! ORDER IS IMPORTANT
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .csrf((csrf -> csrf.disable())) // ! to use POST,PATCH,DELETE without
                                // .csrf(csrf ->
                                // csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                                .authorizeHttpRequests((authz) -> authz
                                                .requestMatchers("/", "index").permitAll()
                                                // ? user ONLYYY can use it
                                                .requestMatchers("/api/**").hasRole(theRoles.USER.name())

                                                // .requestMatchers(HttpMethod.DELETE, "/management/**")
                                                // .hasAuthority(thePermissions.COURSE_WRITE.getPermission())

                                                // .requestMatchers(HttpMethod.POST, "/management/**")
                                                // .hasAuthority(thePermissions.COURSE_WRITE.getPermission())

                                                // .requestMatchers(HttpMethod.PUT, "/management/**")
                                                // .hasAuthority(thePermissions.COURSE_WRITE.getPermission())
                                                // ? admin , admintrainee ONLYYY can use it
                                                // .requestMatchers(HttpMethod.GET, "/management/**")
                                                // .hasAnyRole(theRoles.ADMIN.name(), theRoles.ADMINTRAINEE.name())

                                                .anyRequest().authenticated())

                                .formLogin(t -> {
                                        t.loginPage("/login").permitAll();
                                        t.defaultSuccessUrl("/allstudents", true);
                                        t.passwordParameter("password");
                                        t.usernameParameter("username");
                                })
                                // ! rememeber-me inside the Payload in Network Console
                                // ! Must be stored in database use ==>
                                // tokenRepository(persistentTokenRepository())
                                // ! will be stored in cookie SessionId == remember-me
                                .rememberMe(t -> {
                                        t.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21)); // 2 weeks
                                        t.key("somethingverysecured"); // to encrypt the cookie
                                        t.rememberMeParameter("remember-me"); // ! name of the input in the form
                                        t.rememberMeCookieName("remember-me");
                                })

                                // ! Logout
                                // ! Session id will be deleted
                                // ! Cookie will be deleted
                                // ! redirect to /login
                                .logout(t -> {
                                        t.logoutUrl("/logout");
                                        t.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"));
                                        t.clearAuthentication(true);
                                        t.invalidateHttpSession(true);
                                        t.deleteCookies("JSESSIONID", "remember-me");
                                        t.logoutSuccessUrl("/login");
                                });

                return http.build();
        }

        // !Use UserDetails Sevice to config Users
        // ! InMemoryUserDetailsManager to store users in memory
        // ! badal el application.properties
        @Bean
        protected UserDetailsService userDetailsService() {
                UserDetails abouromia = User.builder()
                                .username("abouromia")
                                .password(passwordEncoder.encode("abouromia")) // ! Password Must be Encoded
                                .authorities(theRoles.USER.getGrandtedAuthorties())
                                // .roles(theRoles.USER.name()) // ! Role Must be in theRoles Enum
                                .build();
                UserDetails theAdmin = User.builder()
                                .username("admin")
                                .password(passwordEncoder.encode("admin"))
                                .authorities(theRoles.ADMIN.getGrandtedAuthorties())
                                // .roles(theRoles.ADMIN.name())
                                .build();
                UserDetails theAdminTrainee = User.builder()
                                .username("tren")
                                .password(passwordEncoder.encode("tren"))
                                .authorities(theRoles.ADMINTRAINEE.getGrandtedAuthorties())
                                // .roles(theRoles.ADMINTRAINEE.name())
                                .build();
                return new InMemoryUserDetailsManager(abouromia, theAdmin, theAdminTrainee);

        }
}

// hena 3mlt configuration class 3shan a3ml bean mn el security filter chain
// hena mafesh /login wala /logout el spring bydhaly
// kol request byt3mlha authentication w byb3t el username w el password