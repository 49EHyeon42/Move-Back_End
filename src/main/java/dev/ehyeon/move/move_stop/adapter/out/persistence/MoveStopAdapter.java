package dev.ehyeon.move.move_stop.adapter.out.persistence;

import dev.ehyeon.move.common.PersistenceAdapter;
import dev.ehyeon.move.move_stop.application.port.out.RegisterMoveStopPort;
import dev.ehyeon.move.move_stop.application.port.out.SearchMoveStopPort;
import dev.ehyeon.move.move_stop.domain.MoveStop;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoveStopAdapter implements RegisterMoveStopPort, SearchMoveStopPort {

    private final MoveStopRepository moveStopRepository;
    private final MoveStopMapper moveStopMapper;

    @Override
    public MoveStop registerMoveStop(String name, String address, double latitude, double longitude) {
        MoveStopEntity savedMoveStopEntity = moveStopRepository.save(new MoveStopEntity(name, address, latitude, longitude));

        return moveStopMapper.mapMoveStopEntityToMoveStop(savedMoveStopEntity);
    }

    @Override
    public List<MoveStop> searchMoveStopByLatitudeBetweenAndLongitudeBetween(double latitude1, double latitude2, double longitude1, double longitude2) {
        List<MoveStopEntity> foundMoveStopEntities = moveStopRepository.findMoveStopByLatitudeBetweenAndLongitudeBetween(latitude1, latitude2, longitude1, longitude2);

        return foundMoveStopEntities.stream()
                .map(moveStopMapper::mapMoveStopEntityToMoveStop)
                .collect(Collectors.toList());
    }
}
