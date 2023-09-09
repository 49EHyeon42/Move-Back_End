package dev.ehyeon.move.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // TODO add Role
}
