package com.codeproj.recipesimplifierbase.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.codeproj.recipesimplifierbase.security.TokenHelper;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private TokenHelper tokenHelper;

    private UserDetailsService userDetailsService;

    public TokenAuthenticationFilter(TokenHelper tokenHelper, UserDetailsService userDetailsService) {
        this.tokenHelper = tokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        String authToken = tokenHelper.getToken(request);
        
        if (authToken == null) {
        	chain.doFilter(request, response);
        	return;
        }

        String username = tokenHelper.getUsernameFromToken(authToken);
        if (username == null) {
        	chain.doFilter(request, response);
        	return;
        }
      
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (tokenHelper.validateToken(authToken, userDetails)) {
            TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
            authentication.setToken(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        chain.doFilter(request, response);
    }

}