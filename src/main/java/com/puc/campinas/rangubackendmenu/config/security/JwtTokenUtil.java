package com.puc.campinas.rangubackendmenu.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.Serializable;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil implements Serializable {

  @Value("${jwt.secret}")
  private String secret;


  protected String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken;
    }
    return null;
  }

  public Authentication getAuthentication(String token) {
    Claims claims = getAllClaimsFromToken(token);
    String userEmail = claims.get("sub", String.class);
    String type = claims.get("type", String.class);

    val authority = new SimpleGrantedAuthority("ROLE_" + type);
    val userDetails = org.springframework.security.core.userdetails.User.withUsername(userEmail)
        .password("")
        .authorities(Collections.singleton(authority))
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(false)
        .build();

    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

}

