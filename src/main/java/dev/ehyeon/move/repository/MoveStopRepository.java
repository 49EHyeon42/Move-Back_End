package dev.ehyeon.move.repository;

import dev.ehyeon.move.entity.MoveStop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoveStopRepository extends JpaRepository<MoveStop, Long> {

    List<MoveStop> findMoveStopByAddress(String address);

    List<MoveStop> findMoveStopByLatitudeBetweenAndLongitudeBetween(double latitude1, double latitude2, double longitude1, double longitude2);
}
