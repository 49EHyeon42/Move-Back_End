package dev.ehyeon.move.move_stop.application.port.in;

import java.util.List;

public interface SearchMoveStopUseCase {

    List<SearchMoveStopResponse> searchMoveStop(SearchMoveStopRequest request);
}
