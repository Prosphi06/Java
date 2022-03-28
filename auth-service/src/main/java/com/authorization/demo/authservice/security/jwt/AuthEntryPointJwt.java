package com.authorization.demo.authservice.security.jwt;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Log4j2
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    //This method will be triggerd anytime unauthenticated User requests a secured HTTP resource and an AuthenticationException is thrown
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.error("Unauthorized error: {}", authException.getMessage());
        //SC_UNAUTHORIZED is the 401 Status code. It indicates that the request requires HTTP authentication.
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }
}
