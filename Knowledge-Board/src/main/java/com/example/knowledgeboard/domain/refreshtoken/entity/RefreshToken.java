package com.example.knowledgeboard.domain.refreshtoken.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@RedisHash
public class RefreshToken {

    @Id
    private String id;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long refreshExpiration;

    public void reissue(String refreshToken, Long refreshExpiration) {
        this.refreshToken = refreshToken;
        this.refreshExpiration = refreshExpiration;
    }

}
