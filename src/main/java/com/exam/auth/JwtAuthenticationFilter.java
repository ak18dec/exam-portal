package com.exam.auth;

import com.exam.user.exception.UserNotFoundException;
import com.exam.user.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtill jwtUtill;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestHeaderToken = request.getHeader("Authorization");
        System.out.println("Request Header Token: " + requestHeaderToken);
        String usernameOrEmail = null;
        String jwtToken = null;
        UserDetails userDetails = null;

        if (requestHeaderToken != null && requestHeaderToken.startsWith("Bearer ")) {

            jwtToken = requestHeaderToken.substring(7);  //removing string bearer with ending space from token

            usernameOrEmail = this.jwtUtill.extractUsername(jwtToken);

        }

        //validated
        if (usernameOrEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if(usernameOrEmail.contains("@")){
                try {
                     userDetails = this.userDetailsService.loadUserByEmail(usernameOrEmail);
                } catch (UserNotFoundException e) {
                    e.printStackTrace();
                }
            }else{
                userDetails = this.userDetailsService.loadUserByUsername(usernameOrEmail);
            }
            if (this.jwtUtill.validateToken(jwtToken, userDetails)) {

                //valid token
                UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);

            }
        }

        filterChain.doFilter(request, response);
    }
}
