package dev.ehyeon.move.security.local;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SignInResponse {

    @JsonProperty(value = "access token")
    private String accessToken;

    public SignInResponse(String jwt) {
        this.accessToken = "Bearer " + jwt;
    }
}
