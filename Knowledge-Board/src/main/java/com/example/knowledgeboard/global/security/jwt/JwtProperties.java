package com.example.knowledgeboard.global.security.jwt;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private final String secretKet;
    private final Long accessExp;
    private final Long refreshExp;
    private final String header;
    private final String prefix;

    public JwtProperties(String secretKet, Long accessExp, Long refreshExp, String header, String prefix) {
        this.secretKet = Base64.getEncoder().encodeToString(secretKet.getBytes(StandardCharsets.UTF_8));
        this.accessExp = accessExp;
        this.refreshExp = refreshExp;
        this.header = header;
        this.prefix = prefix;
    }

}
