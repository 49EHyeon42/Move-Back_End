package dev.ehyeon.move.record.adapter.out.persistence;

import dev.ehyeon.move.record.domain.Record;
import org.springframework.stereotype.Component;

@Component
public class RecordMapper {

    Record mapRecordEntityToRecord(RecordEntity recordEntity) {
        return new Record(
                recordEntity.getId(),
                recordEntity.getMember().getId(),
                recordEntity.getSavedDate(),
                recordEntity.getElapsedTime(),
                recordEntity.getTotalTravelDistance(),
                recordEntity.getAverageSpeed(),
                recordEntity.getStep(),
                recordEntity.getCalorieConsumption()
        );
    }
}
