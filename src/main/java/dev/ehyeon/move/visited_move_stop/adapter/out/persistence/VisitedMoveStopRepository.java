package dev.ehyeon.move.visited_move_stop.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitedMoveStopRepository extends JpaRepository<VisitedMoveStopEntity, Long> {

    Optional<VisitedMoveStopEntity> findVisitedMoveStopByMemberIdAndMoveStopEntityId(Long memberId, Long moveStopEntityId);

    boolean existsVisitedMoveStopByMemberIdAndMoveStopEntityId(Long memberId, Long moveStopEntityId);
}
