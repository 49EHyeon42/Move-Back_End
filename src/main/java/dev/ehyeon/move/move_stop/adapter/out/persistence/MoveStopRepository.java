package dev.ehyeon.move.move_stop.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoveStopRepository extends JpaRepository<MoveStopEntity, Long> {

    List<MoveStopEntity> findMoveStopByLatitudeBetweenAndLongitudeBetween(double latitude1, double latitude2, double longitude1, double longitude2);
}
