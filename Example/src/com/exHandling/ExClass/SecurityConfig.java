/*
 * package com.springsecurity.SecurityConfig;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Qualifier; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.authentication.AuthenticationProvider; import
 * org.springframework.security.authentication.dao.DaoAuthenticationProvider;
 * import
 * org.springframework.security.config.annotation.authentication.configuration.
 * AuthenticationConfiguration;
 * 
 * import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * 
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.http.SessionCreationPolicy; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.security.web.SecurityFilterChain; import
 * org.springframework.security.web.authentication.
 * UsernamePasswordAuthenticationFilter; import
 * org.springframework.web.servlet.HandlerExceptionResolver;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SecurityConfig {
 * 
 * @Autowired public SpringEntryPoint authEntryPoint;
 * 
 * @Autowired
 * 
 * @Qualifier("handlerExceptionResolver") public HandlerExceptionResolver
 * exceptionResolver;
 * 
 * @Bean public JwtAuthenticationFilter authenticationFilter() { return new
 * JwtAuthenticationFilter(exceptionResolver); }
 * 
 * @Bean public UserDetailsService userDetailsService() { return new
 * CustomUserDetailsService(); }
 * 
 * @Bean public SecurityFilterChain seFilterChain(HttpSecurity http) throws
 * Exception {
 * 
 * 
 * return http.csrf(csrf -> csrf.disable())
 * 
 * .authorizeHttpRequests(authorizeRequests -> authorizeRequests
 * 
 * .requestMatchers("/test", "/generateToken").permitAll() //
 * .requestMatchers("/user").hasAnyRole("User") // .anyRequest().authenticated()
 * ) .sessionManagement(session ->
 * session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
 * .exceptionHandling(ex -> ex.authenticationEntryPoint(authEntryPoint))
 * .authenticationProvider(authenticationProvider())
 * .addFilterBefore(authenticationFilter(),
 * UsernamePasswordAuthenticationFilter.class)
 * 
 * .build();
 * 
 * }
 * 
 * @Bean public PasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * @Bean public AuthenticationProvider authenticationProvider() {
 * DaoAuthenticationProvider authenticationProvider = new
 * DaoAuthenticationProvider();
 * authenticationProvider.setUserDetailsService(userDetailsService());
 * authenticationProvider.setPasswordEncoder(passwordEncoder()); return
 * authenticationProvider; }
 * 
 * @Bean public AuthenticationManager
 * authenticationManager(AuthenticationConfiguration config) throws Exception {
 * return config.getAuthenticationManager(); } }
 */


