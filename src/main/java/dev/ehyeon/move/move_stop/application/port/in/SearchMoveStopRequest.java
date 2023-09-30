package dev.ehyeon.move.move_stop.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
public class SearchMoveStopRequest {

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
}
