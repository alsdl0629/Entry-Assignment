package com.example.knowledgeboard.security.jwt;

import com.example.knowledgeboard.dto.auth.response.TokenResponse;
import com.example.knowledgeboard.entity.refreshtoken.RefreshToken;
import com.example.knowledgeboard.entity.refreshtoken.RefreshTokenRepository;
import com.example.knowledgeboard.exception.ExpiredTokenException;
import com.example.knowledgeboard.exception.InvalidTokenException;
import com.example.knowledgeboard.security.auth.AuthDetailsService;
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

    public TokenResponse generateToken(String accountId) {
        String accessToken = generateAccessToken(accountId);
        String refreshToken = generateRefreshToken(accountId);

        refreshTokenRepository.save(RefreshToken.builder()
                .id(accountId)
                .refreshToken(refreshToken)
                .refreshExpiration(jwtProperties.getRefreshExp())
                .build());

        return new TokenResponse(accessToken, refreshToken);
    }

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
        return Jwts.builder()
                .setSubject(accountId)
                .claim("type", "refresh")
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKet())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getRefreshExp() * 1000))
                .setIssuedAt(new Date())
                .compact();
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
