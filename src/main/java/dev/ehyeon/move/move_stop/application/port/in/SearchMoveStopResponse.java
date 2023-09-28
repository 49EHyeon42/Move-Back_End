package dev.ehyeon.move.move_stop.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SearchMoveStopResponse {

    private final String name;
    private final double latitude;
    private final double longitude;
}
