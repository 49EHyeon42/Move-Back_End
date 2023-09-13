package dev.ehyeon.move.service;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.entity.Record;
import dev.ehyeon.move.repository.MemberRepository;
import dev.ehyeon.move.repository.RecordRepository;
import dev.ehyeon.move.request.SaveRecordRequest;
import dev.ehyeon.move.security.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecordService {

    private final MemberRepository memberRepository;

    private final RecordRepository recordRepository;

    @Transactional
    public void saveRecordByMemberEmail(String email, SaveRecordRequest request) {
        Member foundMember = memberRepository.findMemberByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        recordRepository.save(new Record(
                foundMember,
                request.getSavedDate(),
                request.getElapsedTime(),
                request.getTotalTravelDistance(),
                request.getAverageSpeed(),
                request.getStep(),
                request.getCalorieConsumption()));
    }
}
