package dev.ehyeon.move.service;

import dev.ehyeon.move.entity.MoveStop;
import dev.ehyeon.move.repository.MoveStopRepository;
import dev.ehyeon.move.request.RegisterMoveStopRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MoveStopService {

    private final MoveStopRepository moveStopRepository;

    @Transactional
    public void registerMoveStop(RegisterMoveStopRequest request) {
        moveStopRepository.save(new MoveStop(request.getName(), request.getAddress(), request.getLatitude(), request.getLongitude()));
    }
}
