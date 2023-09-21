package dev.ehyeon.move.initializer;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.entity.MoveStop;
import dev.ehyeon.move.entity.Role;
import dev.ehyeon.move.repository.MemberRepository;
import dev.ehyeon.move.repository.MoveStopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DBInitializer {

    private final MemberRepository memberRepository;

    private final MoveStopRepository moveStopRepository;

    @PostConstruct
    public void postConstruct() {
        memberRepository.save(new Member("qa@domain.com", "$2a$10$BprIgyxGevnVlQmGSUVtjuZT7vSBc7MxlvpUTZFBz9AxXmW3sL0ie", Role.MEMBER));

        moveStopRepository.save(new MoveStop("한국교통대학교 문학사", "충청북도 충주시", 36.96662987898937, 127.87232090960241));
        moveStopRepository.save(new MoveStop("한국교통대학교 중앙도서관", "충청북도 충주시", 36.967941962105705, 127.86972223749288));
        moveStopRepository.save(new MoveStop("한국교통대학교 중앙정보관", "충청북도 충주시", 36.969095878473745, 127.86964552960447));
        moveStopRepository.save(new MoveStop("한국교통대학교 휴카페", "충청북도 충주시", 36.96981612658972, 127.8709563669459));
        moveStopRepository.save(new MoveStop("한국교통대학교 건축관", "충청북도 충주시", 36.97133272939023, 127.87179904594674));
    }
}
