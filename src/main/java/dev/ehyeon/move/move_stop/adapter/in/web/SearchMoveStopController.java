package dev.ehyeon.move.move_stop.adapter.in.web;

import dev.ehyeon.move.move_stop.application.port.in.SearchMoveStopRequest;
import dev.ehyeon.move.move_stop.application.port.in.SearchMoveStopResponse;
import dev.ehyeon.move.move_stop.application.port.in.SearchMoveStopUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchMoveStopController {

    private final SearchMoveStopUseCase useCase;

    @GetMapping("/move-stop")
    public List<SearchMoveStopResponse> searchMoveStop(SearchMoveStopRequest request) {
        return useCase.searchMoveStop(request);
    }
}
