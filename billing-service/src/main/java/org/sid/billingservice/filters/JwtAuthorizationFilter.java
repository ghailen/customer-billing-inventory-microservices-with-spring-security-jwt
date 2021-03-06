package org.sid.billingservice.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.sid.billingservice.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationToken = request.getHeader(JWTUtil.AUTH_HEADER);
       // il faut que le filtre ne prend pas en consideration refreshtoken api url
        if (request.getServletPath().contains("/customers/")){
          // pass à l'autre filter
           filterChain.doFilter(request,response);
       }else {
           if (authorizationToken != null && authorizationToken.startsWith(JWTUtil.PREFIX)) {
               //7 c'est le nmr de caracterer de mot bearer
               try {
                   String jwt = authorizationToken.substring(7);
                   Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
                   JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                   DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                   String username = decodedJWT.getSubject();
                   String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                   Collection<GrantedAuthority> authorities = new ArrayList<>();
                   for (String r : roles) {
                       authorities.add(new SimpleGrantedAuthority(r));
                   }
                   UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);

                   //authentifier l'utilisateuur
                   SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                   // pass au filtre suivant
                   filterChain.doFilter(request, response);
               } catch (Exception e) {
                   response.setHeader("error-message", e.getMessage());
                   response.sendError(HttpServletResponse.SC_FORBIDDEN);
               }

           }else {
               filterChain.doFilter(request, response);
           }
       }


    }
}
