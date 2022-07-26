package com.example.knowledgeboard.global.security.jwt;

import com.example.knowledgeboard.domain.refreshtoken.entity.RefreshToken;
import com.example.knowledgeboard.domain.refreshtoken.repository.RefreshTokenRepository;
import com.example.knowledgeboard.global.exception.ExpiredTokenException;
import com.example.knowledgeboard.global.exception.InvalidTokenException;
import com.example.knowledgeboard.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    public String generateAccessToken(String accountId) {
        return Jwts.builder()
                .setSubject(accountId)
                .claim("type", "access")
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKet())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getAccessExp() * 1000))
                .setIssuedAt(new Date())
                .compact();
    }

    public String generateRefreshToken(String accountId) {

        String refreshToken = Jwts.builder()
                .setSubject(accountId)
                .claim("type", "refresh")
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKet())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getRefreshExp() * 1000))
                .setIssuedAt(new Date())
                .compact();

        refreshTokenRepository.save(RefreshToken.builder()
                .id(accountId)
                .refreshToken(refreshToken)
                .refreshExpiration(jwtProperties.getRefreshExp())
                .build());

        return refreshToken;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(getBody(token).getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Claims getBody(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKet())
                    .parseClaimsJws(token)
                    .getBody();
        }catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        }catch (MalformedJwtException | SignatureException e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtProperties.getHeader());

        if(bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix())) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
