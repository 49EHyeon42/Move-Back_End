package dev.ehyeon.move.move_stop.adapter.in.web;

import dev.ehyeon.move.move_stop.application.port.in.RegisterMoveStopRequest;
import dev.ehyeon.move.move_stop.application.port.in.RegisterMoveStopResponse;
import dev.ehyeon.move.move_stop.application.port.in.RegisterMoveStopUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RegisterMoveStopController {

    private final RegisterMoveStopUseCase useCase;

    @PostMapping("/move-stop")
    public RegisterMoveStopResponse registerMoveStop(@RequestBody @Valid RegisterMoveStopRequest request) {
        return useCase.registerMoveStop(request);
    }
}
