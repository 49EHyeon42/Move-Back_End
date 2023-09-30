package dev.ehyeon.move.record.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class Record {

    private final long id;
    private final long memberId;
    private final LocalDateTime savedDate;
    private final int elapsedTime;
    private final int totalTravelDistance;
    private final float averageSpeed;
    private final int step;
    private final float calorieConsumption;
}
