package dev.ehyeon.move.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class GetMoveStopRequest {

    // 남서쪽
    @NotNull
    private final double latitude1;

    @NotNull
    private final double longitude1;

    // 북동쪽
    @NotNull
    private final double latitude2;

    @NotNull
    private final double longitude2;

    public GetMoveStopRequest(double latitude1, double longitude1, double latitude2, double longitude2) {
        this.latitude1 = latitude1;
        this.longitude1 = longitude1;
        this.latitude2 = latitude2;
        this.longitude2 = longitude2;
    }
}
