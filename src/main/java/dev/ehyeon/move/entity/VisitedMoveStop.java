//package dev.ehyeon.move.entity;
//
//import dev.ehyeon.move.move_stop.adapter.out.persistence.MoveStopEntity;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Getter
//public class VisitedMoveStop {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;
//
//    @ManyToOne
//    @JoinColumn(name = "move_stop_id")
//    private MoveStopEntity moveStopEntity;
//
//    private LocalDateTime dateOfLastVisit;
//
//    public VisitedMoveStop(Member member, MoveStopEntity moveStopEntity, LocalDateTime dateOfLastVisit) {
//        this.member = member;
//        this.moveStopEntity = moveStopEntity;
//        this.dateOfLastVisit = dateOfLastVisit;
//    }
//
//    public void updateDateOfLastVisit(LocalDateTime dateOfLastVisit) {
//        this.dateOfLastVisit = dateOfLastVisit;
//    }
//}
