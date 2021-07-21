package com.puc.campinas.rangubackendmenu.config.security;

import com.puc.campinas.rangubackendmenu.integration.UserClient;
import feign.FeignException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

  @Autowired
  UserClient userClient;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;


  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    String token = jwtTokenUtil.resolveToken(httpServletRequest);
    validateToken(token);
    Authentication auth = jwtTokenUtil.getAuthentication(token.substring(7));
    SecurityContextHolder.getContext().setAuthentication(auth);
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

  private void validateToken(String token) {
    try {
      userClient.validateToken(token);
    } catch (FeignException e) {
      throw e;
    }
  }
}
