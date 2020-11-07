package com.example.championship.jwt;

import com.example.championship.security.SecurityUserDetailsManager;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static io.jsonwebtoken.lang.Strings.hasText;

@Service
@Slf4j
public class TokenAuthenticationService {
  private static final String SECRET = "Secret";
  private static final long EXPIRATION_TIME = 864000000; //10 дней
  private static final String TOKEN_PREFIX = "Bearer";
  private static final String HEADER_STRING = "Authorization";

  @Autowired
  private static SecurityUserDetailsManager securityUserDetailsManager;

  public TokenAuthenticationService(SecurityUserDetailsManager securityUserDetailsManager) {
    this.securityUserDetailsManager = securityUserDetailsManager;
  }

  static void addAuthentication(HttpServletResponse response, String username) {
    response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + generateToken(username));
  }

  static Authentication getAuthentication(HttpServletRequest request) {
    String token = getToken(request);
    if (!hasText(token)) {
      return null;
    }
    /* Проверка наличия сессии по токену*/
    String userName = getUsername(token);
    UserDetails user = securityUserDetailsManager.loadUserByUsername(userName);
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        user, null, user.getAuthorities());
    return authentication;
  }

  private static String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }

  private static String getToken(HttpServletRequest request) {
    if (request.getHeader(HEADER_STRING) != null) {
      return request.getHeader(HEADER_STRING).replace(TOKEN_PREFIX + " ", "");
    } else {
      return null;
    }
  }

  public static String getUsername(String token) {
    try {
      return token != null ? Jwts.parser()
          .setSigningKey(SECRET)
          .parseClaimsJws(token)
          .getBody()
          .getSubject() : null;
    } catch (ExpiredJwtException e) {
      log.info("Ошибка обработки токена - токен просрочен : {}", token);
      return null;
    } catch (JwtException e) {
      log.info("Ошибка обработки токена: {}", token);
      return null;
    }
  }

}
