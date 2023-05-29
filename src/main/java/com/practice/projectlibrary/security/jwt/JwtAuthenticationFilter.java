package com.practice.projectlibrary.security.jwt;

import com.practice.projectlibrary.exception.TokenException;
import com.practice.projectlibrary.service.impl.MyUserDetailSeviceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  private final MyUserDetailSeviceImpl userDetailsService;


  @Override
  protected void doFilterInternal(
      @NotNull HttpServletRequest request,
      @NotNull HttpServletResponse response,
      @NotNull FilterChain filterChain) throws ServletException, IOException {

    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String userNameOrEmail;

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    jwt = authHeader.substring(7);

    //extract usernameOrEmail from jwt
    try {
      userNameOrEmail = jwtService.extractUser(jwt);
      jwtService.validateToken(jwt);
    } catch (TokenException ex) {
      throw new TokenException(ex.getMessage());
    }
    if (userNameOrEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetail = userDetailsService.loadUserByUsername(userNameOrEmail);
      if (jwtService.isTokenValid(jwt, userDetail)) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            userDetail,
            null,
            userDetail.getAuthorities()
        );
        authenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }
    filterChain.doFilter(request, response);


  }
}
