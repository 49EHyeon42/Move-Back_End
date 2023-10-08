package dev.ehyeon.move.visited_move_stop.adapter.out.persistence;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class VisitedMoveStopEntityId implements Serializable {

    private Long memberId;
    private Long moveStopEntityId;
}
