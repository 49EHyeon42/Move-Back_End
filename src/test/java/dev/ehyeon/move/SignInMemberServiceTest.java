package dev.ehyeon.move;

import dev.ehyeon.move.security.exception.ExpiredSignInMemberException;
import dev.ehyeon.move.security.local.SignInMemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class SignInMemberServiceTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    SignInMemberService signInMemberService;

    @AfterEach
    void afterEach() {
        signInMemberService.deleteAll();
    }

    @Test
    void updateJwtBySignInMemberEmail() {
        String email = "test@domain.com";
        String jwt = "abcdefghijklmnopqrstuvwxyz";

        // 1. 첫 로그인
        signInMemberService.saveSignInMember(email, jwt);

        signInMemberService.validateSignInMember(email, jwt);

        // 2. 새로운 클라이언트에서 로그인
        signInMemberService.saveSignInMember(email, "new jwt");

        // 3. 이전에 발급한 토큰으로 요청 -> 예외 발생
        assertThatThrownBy(() -> signInMemberService.validateSignInMember(email, jwt))
                .isInstanceOf(ExpiredSignInMemberException.class);
    }
}
