package dev.ehyeon.move.move_stop.adapter.out.persistence;

import dev.ehyeon.move.move_stop.domain.MoveStop;
import org.springframework.stereotype.Component;

@Component
class MoveStopMapper {

    MoveStop mapMoveStopEntityToMoveStop(MoveStopEntity moveStopEntity) {
        return new MoveStop(
                moveStopEntity.getId(),
                moveStopEntity.getName(),
                moveStopEntity.getAddress(),
                moveStopEntity.getLatitude(),
                moveStopEntity.getLongitude());
    }
}
