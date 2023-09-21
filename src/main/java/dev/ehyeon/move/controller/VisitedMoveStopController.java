package dev.ehyeon.move.controller;

import dev.ehyeon.move.request.VisitedMoveStopRequest;
import dev.ehyeon.move.service.VisitedMoveStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// TODO refactor
@RestController
@RequiredArgsConstructor
public class VisitedMoveStopController {

    private final VisitedMoveStopService visitedMoveStopService;

    @PostMapping("/visited_move_stop")
    public void saveOrUpdateVisitedMoveStop(@RequestBody VisitedMoveStopRequest request) {
        visitedMoveStopService.saveOrUpdateVisitedMoveStop(request);
    }
}
