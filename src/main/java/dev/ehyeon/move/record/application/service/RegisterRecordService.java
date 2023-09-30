package dev.ehyeon.move.record.application.service;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.record.application.port.in.RegisterRecordRequest;
import dev.ehyeon.move.record.application.port.in.RegisterRecordResponse;
import dev.ehyeon.move.record.application.port.in.RegisterRecordUseCase;
import dev.ehyeon.move.record.application.port.out.RegisterRecordPort;
import dev.ehyeon.move.record.domain.Record;
import dev.ehyeon.move.repository.MemberRepository;
import dev.ehyeon.move.security.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterRecordService implements RegisterRecordUseCase {

    // TODO refactor, repository to port
    private final MemberRepository memberRepository;
    private final RegisterRecordPort registerRecordPort;

    @Override
    public RegisterRecordResponse registerRecord(String memberEmail, RegisterRecordRequest request) {
        Member foundMember = memberRepository.findMemberByEmail(memberEmail)
                .orElseThrow(MemberNotFoundException::new);

        // TODO null
        Record registeredRecord = registerRecordPort.registerRecord(
                foundMember,
                request.getSavedDate(),
                request.getElapsedTime(),
                request.getTotalTravelDistance(),
                request.getAverageSpeed(),
                request.getStep(),
                request.getCalorieConsumption());

        return mapRecordToRegisterRecordResponse(registeredRecord);
    }

    private RegisterRecordResponse mapRecordToRegisterRecordResponse(Record record) {
        return new RegisterRecordResponse(
                record.getSavedDate(),
                record.getElapsedTime(),
                record.getTotalTravelDistance(),
                record.getAverageSpeed(),
                record.getStep(),
                record.getCalorieConsumption());
    }
}
