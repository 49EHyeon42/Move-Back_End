package dev.ehyeon.move.record.adapter.out.persistence;

import dev.ehyeon.move.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RecordEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private LocalDateTime savedDate;

    @ColumnDefault("0")
    private int elapsedTime;

    @ColumnDefault("0")
    private int totalTravelDistance;

    @ColumnDefault("0")
    private float averageSpeed;

    @ColumnDefault("0")
    private int step;

    @ColumnDefault("0")
    private float calorieConsumption;

    public RecordEntity(Member member, LocalDateTime savedDate, int elapsedTime, int totalTravelDistance, float averageSpeed, int step, float calorieConsumption) {
        this.member = member;
        this.savedDate = savedDate;
        this.elapsedTime = elapsedTime;
        this.totalTravelDistance = totalTravelDistance;
        this.averageSpeed = averageSpeed;
        this.step = step;
        this.calorieConsumption = calorieConsumption;
    }
}
