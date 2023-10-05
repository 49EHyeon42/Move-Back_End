package dev.ehyeon.move.visited_move_stop.application.port.in;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class VisitedMoveStopRequest {

    @NotNull
    private Double memberLatitude;

    @NotNull
    private Double memberLongitude;

    // 남서쪽
    @NotNull
    private Double latitude1;

    @NotNull
    private Double longitude1;

    // 북동쪽
    @NotNull
    private Double latitude2;

    @NotNull
    private Double longitude2;
}
