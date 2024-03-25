/*
 * package com.springsecurity.SecurityConfig;
 * 
 * import java.io.IOException;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Qualifier;
 * 
 * import org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.context.SecurityContextHolder; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.web.authentication.
 * WebAuthenticationDetailsSource; import
 * org.springframework.web.filter.OncePerRequestFilter; import
 * org.springframework.web.servlet.HandlerExceptionResolver;
 * 
 * import com.springsecurity.UserService.JwtService; import
 * com.springsecurity.customException.UserIdNotFound;
 * 
 * import jakarta.servlet.FilterChain; import jakarta.servlet.ServletException;
 * import jakarta.servlet.http.HttpServletRequest; import
 * jakarta.servlet.http.HttpServletResponse; import lombok.extern.slf4j.Slf4j;
 * 
 * @Slf4j // //@Configuration public class JwtAuthenticationFilter extends
 * OncePerRequestFilter {
 * 
 * @Autowired public JwtService jwtService;
 * 
 * @Autowired public UserDetailsService userDetailsService;
 * 
 * @Qualifier("handlerExceptionResolver") public HandlerExceptionResolver
 * exceptionResolver;
 * 
 * public JwtAuthenticationFilter(HandlerExceptionResolver response) {
 * this.exceptionResolver = response; }
 * 
 * @Override protected void doFilterInternal(HttpServletRequest request,
 * HttpServletResponse response, FilterChain filterChain) throws
 * ServletException, IOException {
 * 
 * try {
 * 
 * String header = request.getHeader("Authorization");
 * 
 * if (header == null || !header.startsWith("Bearer ")) { return; }
 * 
 * String Token = header.substring(7);
 * 
 * String emailId = jwtService.extractEmailId(Token);
 * 
 * if (emailId == null) {
 * 
 * throw new UserIdNotFound("User not found" + emailId); }
 * System.out.println("User name after if :" + emailId);
 * 
 * UserDetails ubName = userDetailsService.loadUserByUsername(emailId);
 * 
 * UsernamePasswordAuthenticationToken user = new
 * UsernamePasswordAuthenticationToken(ubName.getUsername(),
 * ubName.getPassword(), ubName.getAuthorities());
 * 
 * if (!user.isAuthenticated()) {
 * 
 * log.info("User not authenticated...!"); }
 * 
 * user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
 * SecurityContextHolder.getContext().setAuthentication(user);
 * filterChain.doFilter(request, response);
 * 
 * } catch (Exception e) {
 * 
 * log.info(e.getMessage());
 * 
 * exceptionResolver.resolveException(request, response, null, e);
 * log.info(exceptionResolver.toString()); }
 * 
 * }
 * 
 * }
 */
package com;

