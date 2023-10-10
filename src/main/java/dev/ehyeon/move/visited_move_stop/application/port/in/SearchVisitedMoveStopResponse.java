package dev.ehyeon.move.visited_move_stop.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SearchVisitedMoveStopResponse {

    private final String name;
    private final double latitude;
    private final double longitude;
    private final boolean visited;
}
