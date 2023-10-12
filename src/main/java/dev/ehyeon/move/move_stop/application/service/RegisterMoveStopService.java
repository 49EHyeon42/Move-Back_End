package dev.ehyeon.move.move_stop.application.service;

import dev.ehyeon.move.move_stop.application.port.in.RegisterMoveStopRequest;
import dev.ehyeon.move.move_stop.application.port.in.RegisterMoveStopResponse;
import dev.ehyeon.move.move_stop.application.port.in.RegisterMoveStopUseCase;
import dev.ehyeon.move.move_stop.application.port.out.RegisterMoveStopPort;
import dev.ehyeon.move.move_stop.domain.MoveStop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterMoveStopService implements RegisterMoveStopUseCase {

    private final RegisterMoveStopPort port;

    @Override
    public RegisterMoveStopResponse registerMoveStop(RegisterMoveStopRequest request) {
        MoveStop registeredMoveStop = port.registerMoveStop(
                request.getName(),
                request.getAddress(),
                request.getLatitude(),
                request.getLongitude(),
                request.getEarnMileage(),
                request.getCooldownTime());

        return mapMoveStopToRegisterMoveStopResponse(registeredMoveStop);
    }

    private RegisterMoveStopResponse mapMoveStopToRegisterMoveStopResponse(MoveStop moveStop) {
        return new RegisterMoveStopResponse(
                moveStop.getName(),
                moveStop.getAddress(),
                moveStop.getLatitude(),
                moveStop.getLongitude(),
                moveStop.getEarnMileage(),
                moveStop.getCooldownTime());
    }
}
