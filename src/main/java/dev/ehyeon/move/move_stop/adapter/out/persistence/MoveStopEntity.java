package dev.ehyeon.move.move_stop.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "move_stop")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MoveStopEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private int earnMileage;

    @Column(nullable = false)
    private int cooldownTime;

    public MoveStopEntity(String name, String address, double latitude, double longitude, int earnMileage, int cooldownTime) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.earnMileage = earnMileage;
        this.cooldownTime = cooldownTime;
    }
}
