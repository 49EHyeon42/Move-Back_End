package dev.ehyeon.move.record.adapter.out.persistence;

import dev.ehyeon.move.common.PersistenceAdapter;
import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.record.application.port.out.RegisterRecordPort;
import dev.ehyeon.move.record.application.port.out.SearchRecordPort;
import dev.ehyeon.move.record.domain.Record;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class RecordAdapter implements RegisterRecordPort, SearchRecordPort {

    private final RecordRepository repository;
    private final RecordMapper mapper;

    @Override
    public Record registerRecord(Member member, LocalDateTime savedDate, int elapsedTime, int totalTravelDistance, float averageSpeed, int step, float calorieConsumption) {
        RecordEntity savedRecord = repository.save(new RecordEntity(member, savedDate, elapsedTime, totalTravelDistance, averageSpeed, step, calorieConsumption));

        return mapper.mapRecordEntityToRecord(savedRecord);
    }

    @Override
    public List<Record> searchRecordByMemberAndLocalDateTimeBetween(Member member, LocalDateTime from, LocalDateTime to) {
        List<RecordEntity> foundRecordEntities = repository.findAllRecordByMemberAndSavedDateBetween(member, from, to);

        return foundRecordEntities.stream()
                .map(mapper::mapRecordEntityToRecord)
                .collect(Collectors.toList());
    }
}
