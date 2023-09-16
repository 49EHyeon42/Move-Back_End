package dev.ehyeon.move.security.local;

import dev.ehyeon.move.entity.SignInMember;
import dev.ehyeon.move.repository.SignInMemberRepository;
import dev.ehyeon.move.security.exception.ExpiredSignInMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignInMemberService {

    private final SignInMemberRepository signInMemberRepository;

    @Transactional
    public void saveSignInMember(String email, String jwt) {
        signInMemberRepository.save(new SignInMember(email, jwt));
    }

    public void validateSignInMember(String email, String jwt) {
        SignInMember foundSignInMember = signInMemberRepository.findById(email).orElseThrow(ExpiredSignInMemberException::new);

        if (!foundSignInMember.getJwt().equals(jwt)) {
            throw new ExpiredSignInMemberException();
        }
    }

    // 테스트용
    public void deleteAll() {
        signInMemberRepository.deleteAll();
    }
}
