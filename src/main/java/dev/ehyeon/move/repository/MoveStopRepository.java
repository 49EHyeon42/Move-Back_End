package dev.ehyeon.move.repository;

import dev.ehyeon.move.entity.MoveStop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoveStopRepository extends JpaRepository<MoveStop, Long> {
}
