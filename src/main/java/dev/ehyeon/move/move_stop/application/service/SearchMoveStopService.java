package dev.ehyeon.move.move_stop.application.service;

import dev.ehyeon.move.move_stop.application.port.in.SearchMoveStopRequest;
import dev.ehyeon.move.move_stop.application.port.in.SearchMoveStopResponse;
import dev.ehyeon.move.move_stop.application.port.in.SearchMoveStopUseCase;
import dev.ehyeon.move.move_stop.application.port.out.SearchMoveStopPort;
import dev.ehyeon.move.move_stop.domain.MoveStop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchMoveStopService implements SearchMoveStopUseCase {

    private final SearchMoveStopPort port;

    @Override
    public List<SearchMoveStopResponse> searchMoveStop(SearchMoveStopRequest request) {
        List<MoveStop> moveStops = port.searchMoveStopByLatitudeBetweenAndLongitudeBetween(
                request.getLatitude1(), request.getLatitude2(), request.getLongitude1(), request.getLongitude2());

        return moveStops.stream()
                .map(this::mapMoveStopToSearchMoveStopResponse)
                .collect(Collectors.toList());
    }

    private SearchMoveStopResponse mapMoveStopToSearchMoveStopResponse(MoveStop moveStop) {
        return new SearchMoveStopResponse(moveStop.getName(), moveStop.getLatitude(), moveStop.getLongitude());
    }
}
