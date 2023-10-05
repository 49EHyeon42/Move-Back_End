package dev.ehyeon.move.visited_move_stop.adapter.in.web;

import dev.ehyeon.move.visited_move_stop.application.port.in.VisitedMoveStopRequest;
import dev.ehyeon.move.visited_move_stop.application.service.VisitedMoveStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VisitedMoveStopController {

    private final VisitedMoveStopService visitedMoveStopService;

    @PostMapping("/api/visited_move_stop")
    public void saveOrUpdateVisitedMoveStop(@RequestBody VisitedMoveStopRequest request) {
        visitedMoveStopService.saveOrUpdateVisitedMoveStop(getMemberEmail(), request);
    }

    private String getMemberEmail() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
