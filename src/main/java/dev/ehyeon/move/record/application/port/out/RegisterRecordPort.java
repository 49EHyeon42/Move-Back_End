package dev.ehyeon.move.record.application.port.out;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.record.domain.Record;

import java.time.LocalDateTime;

public interface RegisterRecordPort {

    Record registerRecord(Member member, LocalDateTime savedDate, int elapsedTime, int totalTravelDistance, float averageSpeed, int step, float calorieConsumption);
}
