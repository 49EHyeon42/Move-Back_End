package dev.ehyeon.move.repository;

import dev.ehyeon.move.entity.MoveStop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoveStopRepository extends JpaRepository<MoveStop, Long> {

    List<MoveStop> findMoveStopByAddress(String address);
}
