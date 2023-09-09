package dev.ehyeon.move.security.local;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SignInResponse {

    // TODO refactor name
    private String accessToken;

    public SignInResponse(String jwt) {
        this.accessToken = "Bearer " + jwt;
    }
}
