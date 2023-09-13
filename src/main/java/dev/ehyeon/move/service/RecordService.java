package dev.ehyeon.move.service;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.entity.Record;
import dev.ehyeon.move.repository.MemberRepository;
import dev.ehyeon.move.repository.RecordRepository;
import dev.ehyeon.move.request.SaveRecordRequest;
import dev.ehyeon.move.response.GetRecordResponse;
import dev.ehyeon.move.security.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<GetRecordResponse> findAllRecordByMemberEmailAndSavedDateBetween(String email, LocalDateTime from, LocalDateTime to) {
        Member foundMember = memberRepository.findMemberByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        List<Record> foundRecords = recordRepository.findAllRecordByMemberIdAndSavedDateBetween(foundMember.getId(), from, to);

        List<GetRecordResponse> recordResponses = new ArrayList<>();

        for (Record foundRecord : foundRecords) {
            recordResponses.add(new GetRecordResponse(foundRecord.getSavedDate(), foundRecord.getElapsedTime(), foundRecord.getTotalTravelDistance(),
                    foundRecord.getAverageSpeed(), foundRecord.getStep(), foundRecord.getCalorieConsumption()));
        }

        return recordResponses;
    }
}
