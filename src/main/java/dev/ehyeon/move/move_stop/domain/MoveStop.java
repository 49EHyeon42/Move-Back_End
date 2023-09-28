package dev.ehyeon.move.move_stop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MoveStop {

    private final Long id;
    private final String name;
    private final String address;
    private final double latitude;
    private final double longitude;
}
