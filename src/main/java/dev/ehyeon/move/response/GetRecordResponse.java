package dev.ehyeon.move.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetRecordResponse {

    private final LocalDateTime savedDate;
    private final int elapsedTime;
    private final int totalTravelDistance;
    private final float averageSpeed;
    private final int step;
    private final float calorieConsumption;

    public GetRecordResponse(LocalDateTime savedDate, int elapsedTime, int totalTravelDistance, float averageSpeed, int step, float calorieConsumption) {
        this.savedDate = savedDate;
        this.elapsedTime = elapsedTime;
        this.totalTravelDistance = totalTravelDistance;
        this.averageSpeed = averageSpeed;
        this.step = step;
        this.calorieConsumption = calorieConsumption;
    }
}
