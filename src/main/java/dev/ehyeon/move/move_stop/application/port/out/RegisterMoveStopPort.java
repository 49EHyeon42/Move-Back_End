package dev.ehyeon.move.move_stop.application.port.out;

import dev.ehyeon.move.move_stop.domain.MoveStop;

public interface RegisterMoveStopPort {

    MoveStop registerMoveStop(String name, String address, double latitude, double longitude);
}
