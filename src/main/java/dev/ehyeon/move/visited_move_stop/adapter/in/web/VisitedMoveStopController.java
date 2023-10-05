package dev.ehyeon.move.visited_move_stop.adapter.in.web;

import dev.ehyeon.move.visited_move_stop.application.port.in.SearchVisitedMoveStopRequest;
import dev.ehyeon.move.visited_move_stop.application.port.in.SearchVisitedMoveStopResponse;
import dev.ehyeon.move.visited_move_stop.application.port.in.VisitedMoveStopRequest;
import dev.ehyeon.move.visited_move_stop.application.service.VisitedMoveStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VisitedMoveStopController {

    private final VisitedMoveStopService visitedMoveStopService;

    @GetMapping("/api/visited_move_stop")
    public List<SearchVisitedMoveStopResponse> searchVisitedMoveStop(SearchVisitedMoveStopRequest request) {
        return visitedMoveStopService.searchVisitedMoveStop(getMemberEmail(), request);
    }

    @PostMapping("/api/visited_move_stop")
    public void saveOrUpdateVisitedMoveStop(@RequestBody VisitedMoveStopRequest request) {
        visitedMoveStopService.saveOrUpdateVisitedMoveStop(getMemberEmail(), request);
    }

    private String getMemberEmail() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
