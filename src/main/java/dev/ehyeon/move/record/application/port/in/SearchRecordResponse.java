package dev.ehyeon.move.record.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class SearchRecordResponse {

    private LocalDateTime savedDate;
    private int elapsedTime;
    private int totalTravelDistance;
    private float averageSpeed;
    private int step;
    private float calorieConsumption;
}
