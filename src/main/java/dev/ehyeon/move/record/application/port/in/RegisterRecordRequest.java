package dev.ehyeon.move.record.application.port.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class RegisterRecordRequest {

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime savedDate;

    @NotNull
    @PositiveOrZero
    private Integer elapsedTime;

    @NotNull
    @PositiveOrZero
    private Integer totalTravelDistance;

    @NotNull
    @PositiveOrZero
    private Float averageSpeed;

    @NotNull
    @PositiveOrZero
    private Integer step;

    @NotNull
    @PositiveOrZero
    private Float calorieConsumption;
}
