package com.example.knowledgeboard.global.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException{
        try {
            filterChain.doFilter(request, response);
        }catch (BusinessException e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(e.getErrorCode().getStatus())
                    .message(e.getErrorCode().getMessage())
                    .build();

            response.setStatus(errorResponse.getStatus());
            response.setContentType("application/json");
            response.getWriter().write(convertObjectToJson(errorResponse));
        }
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

}
