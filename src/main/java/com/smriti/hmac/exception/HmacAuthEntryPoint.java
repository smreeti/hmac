package com.smriti.hmac.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smriti.hmac.dto.response.LoginErrorResponseDTO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smriti on 20/04/20
 */
@Component
public class HmacAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        LoginErrorResponseDTO loginResponse = LoginErrorResponseDTO.builder()
                .status(401)
                .errorMessage("Invalid Password")
                .build();

        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(loginResponse);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        response.getWriter().write(json);
        response.setStatus(loginResponse.getStatus());
        response.flushBuffer();


    }
}
