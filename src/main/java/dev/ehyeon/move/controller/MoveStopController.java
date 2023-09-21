package dev.ehyeon.move.controller;

import dev.ehyeon.move.request.GetMoveStopRequest;
import dev.ehyeon.move.request.RegisterMoveStopRequest;
import dev.ehyeon.move.response.GetMoveStopResponse;
import dev.ehyeon.move.service.MoveStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MoveStopController {

    private final MoveStopService moveStopService;

    @GetMapping("/move_stop")
    public List<GetMoveStopResponse> getMoveStop(GetMoveStopRequest request) {
        return moveStopService.getMoveStop(request);
    }

    @PostMapping("/move_stop")
    public void registerMoveStop(@RequestBody @Validated RegisterMoveStopRequest request) {
        moveStopService.registerMoveStop(request);
    }
}
