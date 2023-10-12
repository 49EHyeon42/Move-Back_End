package dev.ehyeon.move.move_stop.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RegisterMoveStopResponse {

    private final String name;
    private final String address;
    private final double latitude;
    private final double longitude;
    private final Integer earnMileage;
    private final Integer cooldownTime;
}
