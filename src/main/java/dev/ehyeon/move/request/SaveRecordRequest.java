package dev.ehyeon.move.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SaveRecordRequest {

    @JsonProperty(value = "saved_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime savedDate;

    @JsonProperty(value = "elapsed_time")
    private int elapsedTime;

    @JsonProperty(value = "total_travel_distance")
    private int totalTravelDistance;

    @JsonProperty(value = "average_speed")
    private float averageSpeed;

    @JsonProperty(value = "step")
    private int step;

    @JsonProperty(value = "calorie_consumption")
    private float calorieConsumption;
}
