package dev.ehyeon.move.service;

import dev.ehyeon.move.entity.MoveStop;
import dev.ehyeon.move.repository.MoveStopRepository;
import dev.ehyeon.move.request.GetMoveStopRequest;
import dev.ehyeon.move.request.RegisterMoveStopRequest;
import dev.ehyeon.move.response.GetMoveStopResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MoveStopService {

    private final MoveStopRepository moveStopRepository;

    public List<GetMoveStopResponse> getMoveStop(GetMoveStopRequest request) {
        List<MoveStop> foundMoveStops = moveStopRepository.findMoveStopByLatitudeBetweenAndLongitudeBetween(request.getLatitude1(), request.getLatitude2(), request.getLongitude1(), request.getLongitude2());

        List<GetMoveStopResponse> getMoveStopResponses = new ArrayList<>();

        for (MoveStop foundMoveStop : foundMoveStops) {
            getMoveStopResponses.add(new GetMoveStopResponse(foundMoveStop.getName(), foundMoveStop.getLatitude(), foundMoveStop.getLongitude()));
        }

        return getMoveStopResponses;
    }

    @Transactional
    public void registerMoveStop(RegisterMoveStopRequest request) {
        moveStopRepository.save(new MoveStop(request.getName(), request.getAddress(), request.getLatitude(), request.getLongitude()));
    }
}
