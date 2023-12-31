package dev.ehyeon.move.record.application.service;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.record.application.port.in.SearchRecordResponse;
import dev.ehyeon.move.record.application.port.in.SearchRecordUseCase;
import dev.ehyeon.move.record.application.port.in.SearchTotalRecordResponse;
import dev.ehyeon.move.record.application.port.out.SearchRecordPort;
import dev.ehyeon.move.record.domain.Record;
import dev.ehyeon.move.repository.MemberRepository;
import dev.ehyeon.move.security.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchRecordService implements SearchRecordUseCase {

    // TODO refactor, repository to port
    private final MemberRepository memberRepository;
    private final SearchRecordPort searchRecordPort;

    @Override
    public List<SearchRecordResponse> searchRecordByMemberEmailAndLocalDateTimeBetween(String memberEmail, LocalDateTime from, LocalDateTime to) {
        Member foundMember = memberRepository.findMemberByEmail(memberEmail)
                .orElseThrow(MemberNotFoundException::new);

        List<Record> foundRecords = searchRecordPort.searchRecordByMemberAndLocalDateTimeBetween(foundMember, from, to);

        return foundRecords.stream()
                .map(this::mapRecordToSearchRecordResponse)
                .collect(Collectors.toList());
    }

    public SearchTotalRecordResponse searchTotalRecordResponse(String memberEmail) {
        Member foundMember = memberRepository.findMemberByEmail(memberEmail)
                .orElseThrow(MemberNotFoundException::new);

        List<Record> foundRecords = searchRecordPort
                .searchRecordByMemberAndLocalDateTimeBetween(foundMember,
                        LocalDateTime.of(2000, 1, 1, 0, 0),
                        LocalDateTime.now());

        int totalTravelDistance = 0;
        int step = 0;

        for (Record foundRecord : foundRecords) {
            totalTravelDistance += foundRecord.getTotalTravelDistance();
            step += foundRecord.getStep();
        }

        return new SearchTotalRecordResponse(foundMember.getMileage(), totalTravelDistance, step);
    }

    private SearchRecordResponse mapRecordToSearchRecordResponse(Record record) {
        return new SearchRecordResponse(
                record.getSavedDate(),
                record.getElapsedTime(),
                record.getTotalTravelDistance(),
                record.getAverageSpeed(),
                record.getStep(),
                record.getCalorieConsumption());
    }
}
