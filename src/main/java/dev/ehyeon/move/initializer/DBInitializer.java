package dev.ehyeon.move.initializer;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.entity.Role;
import dev.ehyeon.move.move_stop.application.port.in.RegisterMoveStopRequest;
import dev.ehyeon.move.move_stop.application.service.RegisterMoveStopService;
import dev.ehyeon.move.record.adapter.out.persistence.RecordEntity;
import dev.ehyeon.move.record.adapter.out.persistence.RecordRepository;
import dev.ehyeon.move.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DBInitializer {

    private final RegisterMoveStopService registerMoveStopService;

    private final MemberRepository memberRepository;
    private final RecordRepository recordRepository;

    @PostConstruct
    public void postConstruct() {
        Member savedMember = memberRepository.save(new Member("qa@domain.com", "$2a$10$BprIgyxGevnVlQmGSUVtjuZT7vSBc7MxlvpUTZFBz9AxXmW3sL0ie", Role.MEMBER));
        savedMember.addMileage(15);

        savedMember = memberRepository.save(savedMember);

        memberRepository.save(new Member("qa2@domain.com", "$2a$10$TjvvT6xN97m1tm9Z8XTGKu4uRLPxXLQK.doIcRnVzKyhZGuyIhuJy", Role.MEMBER));

        recordRepository.save(new RecordEntity(savedMember, LocalDateTime.of(2023, 9, 1, 0, 0), 1, 1, 1, 1, 1));
        recordRepository.save(new RecordEntity(savedMember, LocalDateTime.of(2023, 9, 3, 0, 0), 1, 1, 1, 1, 1));
        recordRepository.save(new RecordEntity(savedMember, LocalDateTime.of(2023, 9, 5, 0, 0), 1, 1, 1, 1, 1));
        recordRepository.save(new RecordEntity(savedMember, LocalDateTime.of(2023, 9, 5, 1, 0), 2, 2, 2, 2, 2));
        recordRepository.save(new RecordEntity(savedMember, LocalDateTime.of(2023, 9, 30, 1, 0), 100, 100, 7, 100, 100));
        recordRepository.save(new RecordEntity(savedMember, LocalDateTime.of(2023, 9, 30, 2, 0), 200, 200, 5, 601, 250));

        registerMoveStopService.registerMoveStop(new RegisterMoveStopRequest("한국교통대학교 문학사", "충청북도 충주시", 36.96662987898937, 127.87232090960241, 1, 1));
        registerMoveStopService.registerMoveStop(new RegisterMoveStopRequest("한국교통대학교 중앙도서관", "충청북도 충주시", 36.967941962105705, 127.86972223749288, 1, 1));
        registerMoveStopService.registerMoveStop(new RegisterMoveStopRequest("한국교통대학교 중앙정보관", "충청북도 충주시", 36.969095878473745, 127.86964552960447, 1, 1));
        registerMoveStopService.registerMoveStop(new RegisterMoveStopRequest("한국교통대학교 휴카페", "충청북도 충주시", 36.96981612658972, 127.8709563669459, 1, 1));
        registerMoveStopService.registerMoveStop(new RegisterMoveStopRequest("한국교통대학교 건축관", "충청북도 충주시", 36.97133272939023, 127.87179904594674, 1, 1));
        registerMoveStopService.registerMoveStop(new RegisterMoveStopRequest("한국교통대학교 체육관", "충청북도 충주시", 36.96732926159772, 127.87064168543394, 1, 1));
        registerMoveStopService.registerMoveStop(new RegisterMoveStopRequest("한국교통대학교 대학원", "충청북도 충주시", 36.968776631965966, 127.87109612174737, 1, 1));
        registerMoveStopService.registerMoveStop(new RegisterMoveStopRequest("한국교통대학교 종합강의관", "충청북도 충주시", 36.968776631965966, 127.87109612174737, 1, 1));

        registerMoveStopService.registerMoveStop(new RegisterMoveStopRequest("CU 한국교통대학사점", "충청북도 충주시", 36.96628385716505, 127.87495581404085, 1, 1));

        registerMoveStopService.registerMoveStop(new RegisterMoveStopRequest("테스트 1", "", 36.96571187222466, 127.87554160148727, 1, 1));
        registerMoveStopService.registerMoveStop(new RegisterMoveStopRequest("테스트 2", "", 36.965775511748696, 127.87607571181049, 1, 1));
        registerMoveStopService.registerMoveStop(new RegisterMoveStopRequest("테스트 3", "", 36.965965410663294, 127.87659442708726, 1, 1));
    }
}
