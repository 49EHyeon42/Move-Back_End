package dev.ehyeon.move.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class VisitedMoveStop {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "move_stop_id")
    private MoveStop moveStop;

    private LocalDateTime dateOfLastVisit;

    public VisitedMoveStop(Member member, MoveStop moveStop, LocalDateTime dateOfLastVisit) {
        this.member = member;
        this.moveStop = moveStop;
        this.dateOfLastVisit = dateOfLastVisit;
    }

    public void updateDateOfLastVisit(LocalDateTime dateOfLastVisit) {
        this.dateOfLastVisit = dateOfLastVisit;
    }
}
