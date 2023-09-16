package dev.ehyeon.move.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SignInMember {

    @Id
    private String email;

    private String jwt;

    public SignInMember(String email, String jwt) {
        this.email = email;
        this.jwt = jwt;
    }
}
