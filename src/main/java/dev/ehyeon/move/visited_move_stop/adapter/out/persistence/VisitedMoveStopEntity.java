package dev.ehyeon.move.visited_move_stop.adapter.out.persistence;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.move_stop.adapter.out.persistence.MoveStopEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visited_move_stop")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class VisitedMoveStopEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "move_stop_id")
    private MoveStopEntity moveStopEntity;

    private LocalDateTime dateOfLastVisit;

    public VisitedMoveStopEntity(Member member, MoveStopEntity moveStopEntity, LocalDateTime dateOfLastVisit) {
        this.member = member;
        this.moveStopEntity = moveStopEntity;
        this.dateOfLastVisit = dateOfLastVisit;
    }

    public void updateDateOfLastVisit(LocalDateTime dateOfLastVisit) {
        this.dateOfLastVisit = dateOfLastVisit;
    }
}
