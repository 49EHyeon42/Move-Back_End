package dev.ehyeon.move;

import dev.ehyeon.move.security.exception.ExpiredSignInMemberException;
import dev.ehyeon.move.security.local.SignService;
import dev.ehyeon.move.security.local.signin.SignInRequest;
import dev.ehyeon.move.security.local.signup.SignUpRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class SignServiceTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    SignService signService;

    @Test
    void duplicateSignInTest() {
        String email = "test@domain.com";
        String password = "password";

        signService.signUp(new SignUpRequest(email, password));

        // 1. 첫 로그인
        String jwt = signService.signIn(new SignInRequest(email, password));

        assertThat(signService.authenticateMemberByJwt(jwt)).isNotNull();

        // 2. 새로운 클라이어트에서 로그인
        String newJwt = signService.signIn(new SignInRequest(email, password));

        assertThat(signService.authenticateMemberByJwt(newJwt)).isNotNull();

        // 3. 이전에 발급한 토큰으로 요청 -> 예외 발생
        assertThatThrownBy(() -> signService.authenticateMemberByJwt(jwt)).isInstanceOf(ExpiredSignInMemberException.class);
    }
}
