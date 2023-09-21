package dev.ehyeon.move;

import dev.ehyeon.move.entity.MoveStop;
import dev.ehyeon.move.repository.MoveStopRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MoveStopRepositoryTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    MoveStopRepository moveStopRepository;

    @Test
    void findMoveStopByLatitudeBetweenAndLongitudeBetweenTest() {
        moveStopRepository.save(new MoveStop("한국교통대학교 문학사", "충청북도 충주시", 36.96662987898937, 127.87232090960241));
        moveStopRepository.save(new MoveStop("한국교통대학교 중앙도서관", "충청북도 충주시", 36.967941962105705, 127.86972223749288));
        moveStopRepository.save(new MoveStop("한국교통대학교 중앙정보관", "충청북도 충주시", 36.969095878473745, 127.86964552960447));

        List<MoveStop> foundMoveStops = moveStopRepository.findMoveStopByLatitudeBetweenAndLongitudeBetween(36.96743440315661, 36.96944688845355, 127.86889672163078, 127.87092970057876);

        assertThat(foundMoveStops.size()).isEqualTo(2);

        for (MoveStop foundMoveStop : foundMoveStops) {
            log.info("name: " + foundMoveStop.getName());
        }
    }
}
