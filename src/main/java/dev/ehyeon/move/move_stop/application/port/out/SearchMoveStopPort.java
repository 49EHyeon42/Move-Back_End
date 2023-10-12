package dev.ehyeon.move.move_stop.application.port.out;

import dev.ehyeon.move.move_stop.domain.MoveStop;

import java.util.List;

public interface SearchMoveStopPort {

    List<MoveStop> searchMoveStopByLatitudeBetweenAndLongitudeBetween(double latitude1, double latitude2, double longitude1, double longitude2);
}
