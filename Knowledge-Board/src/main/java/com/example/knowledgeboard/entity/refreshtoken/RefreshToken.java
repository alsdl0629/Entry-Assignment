package com.example.knowledgeboard.entity.refreshtoken;

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

}
