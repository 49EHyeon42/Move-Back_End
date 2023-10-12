package dev.ehyeon.move.visited_move_stop.adapter.out.persistence;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.move_stop.adapter.out.persistence.MoveStopEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visited_move_stop")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class VisitedMoveStopEntity {

    @EmbeddedId
    private VisitedMoveStopEntityId id;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @MapsId("moveStopEntityId")
    @ManyToOne
    @JoinColumn(name = "move_stop_id")
    private MoveStopEntity moveStopEntity;

    private LocalDateTime dateOfLastVisit;

    public VisitedMoveStopEntity(Member member, MoveStopEntity moveStopEntity, LocalDateTime dateOfLastVisit) {
        id = new VisitedMoveStopEntityId(member.getId(), moveStopEntity.getId());
        this.member = member;
        this.moveStopEntity = moveStopEntity;
        this.dateOfLastVisit = dateOfLastVisit;
    }

    public void updateDateOfLastVisit(LocalDateTime dateOfLastVisit) {
        this.dateOfLastVisit = dateOfLastVisit;
    }
}
