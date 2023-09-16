package dev.ehyeon.move.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Attendance {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "date", nullable = false)
    private LocalDateTime dateTime;

    public Attendance(Member member, LocalDateTime dateTime) {
        this.member = member;
        this.dateTime = dateTime;
    }
}
