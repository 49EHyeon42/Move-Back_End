package dev.ehyeon.move.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private int mileage;

    public Member(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
        mileage = 0;
    }

    public void addMileage(int mileage) {
        this.mileage += mileage;
    }
}
