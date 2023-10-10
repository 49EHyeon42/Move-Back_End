package dev.ehyeon.move.visited_move_stop.application.service;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.move_stop.adapter.out.persistence.MoveStopEntity;
import dev.ehyeon.move.move_stop.adapter.out.persistence.MoveStopRepository;
import dev.ehyeon.move.repository.MemberRepository;
import dev.ehyeon.move.security.exception.MemberNotFoundException;
import dev.ehyeon.move.visited_move_stop.adapter.out.persistence.VisitedMoveStopEntity;
import dev.ehyeon.move.visited_move_stop.adapter.out.persistence.VisitedMoveStopRepository;
import dev.ehyeon.move.visited_move_stop.application.port.in.SearchVisitedMoveStopRequest;
import dev.ehyeon.move.visited_move_stop.application.port.in.SearchVisitedMoveStopResponse;
import dev.ehyeon.move.visited_move_stop.application.port.in.VisitedMoveStopRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VisitedMoveStopService {

    private final MemberRepository memberRepository;

    private final MoveStopRepository moveStopRepository;

    private final VisitedMoveStopRepository visitedMoveStopRepository;

    // TODO refactor
    @Transactional
    public void saveOrUpdateVisitedMoveStop(String email, VisitedMoveStopRequest request) {
        log.info(request.getLatitude1() + " " + request.getLongitude1() + " " + request.getLatitude2() + " " + request.getLongitude2());

        Member foundMember = memberRepository.findMemberByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        List<MoveStopEntity> foundMoveStopEntities = moveStopRepository
                .findMoveStopByLatitudeBetweenAndLongitudeBetween(request.getLatitude1(), request.getLatitude2(), request.getLongitude1(), request.getLongitude2());

        for (MoveStopEntity foundMoveStopEntity : foundMoveStopEntities) {
            log.info("사용자 주변 move stop : " + foundMoveStopEntity.getName());

            double distance = getDistanceInMeter(request.getMemberLatitude(), request.getMemberLongitude(), foundMoveStopEntity.getLatitude(), foundMoveStopEntity.getLongitude());

            if (distance <= 35) {
                Optional<VisitedMoveStopEntity> foundVisitedMoveStopEntityOptional = visitedMoveStopRepository
                        .findVisitedMoveStopByMemberIdAndMoveStopEntityId(foundMember.getId(), foundMoveStopEntity.getId());

                if (foundVisitedMoveStopEntityOptional.isPresent()) { // 방문 기록이 있다면
                    VisitedMoveStopEntity visitedMoveStopEntity = foundVisitedMoveStopEntityOptional.get();

                    if (visitedMoveStopEntity.getDateOfLastVisit()
                            .plusMinutes(visitedMoveStopEntity.getMoveStopEntity().getCooldownTime()).isBefore(LocalDateTime.now())) {
                        log.info("사용자: " + email + ", 마지막 방문 + 1분 이후 재방문: " + foundMoveStopEntity.getName());

                        visitedMoveStopEntity.updateDateOfLastVisit(LocalDateTime.now());
                    } else {
                        log.info("사용자: " + email + ", 방문: " + foundMoveStopEntity.getName() + ", 등록 안됨");
                    }
                } else { // 첫 방문
                    log.info("사용자: " + email + ", 첫 방문: " + foundMoveStopEntity.getName());

                    // 2번 저장되서 다시 조회
                    if (!visitedMoveStopRepository
                            .existsVisitedMoveStopByMemberIdAndMoveStopEntityId(foundMember.getId(), foundMoveStopEntity.getId())) {
                        visitedMoveStopRepository.save(new VisitedMoveStopEntity(foundMember, foundMoveStopEntity, LocalDateTime.now()));
                    }
                }
            } else {
                log.info(foundMoveStopEntity.getName() + ", distance:" + distance);
            }
        }
    }

    private double getDistanceInMeter(double latitude1, double longitude1, double latitude2, double longitude2) {
        return (radianToDegree(Math.acos(Math.sin(degreeToRadian(latitude1)) * Math.sin(degreeToRadian(latitude2)) +
                Math.cos(degreeToRadian(latitude1)) * Math.cos(degreeToRadian(latitude2)) * Math.cos(degreeToRadian(longitude1 - longitude2)))) *
                60 * 1.1515) * 1609.344;

    }

    private double degreeToRadian(double degree) {
        return (degree * Math.PI / 180.0);
    }

    private double radianToDegree(double radian) {
        return (radian * 180 / Math.PI);
    }

    // TODO refactor
    public List<SearchVisitedMoveStopResponse> searchVisitedMoveStop(String email, SearchVisitedMoveStopRequest request) {
        Member foundMember = memberRepository.findMemberByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        List<SearchVisitedMoveStopResponse> responses = new ArrayList<>();

        List<MoveStopEntity> foundMoveStopEntities = moveStopRepository
                .findMoveStopByLatitudeBetweenAndLongitudeBetween(request.getLatitude1(), request.getLatitude2(), request.getLongitude1(), request.getLongitude2());

        for (MoveStopEntity foundMoveStopEntity : foundMoveStopEntities) {
            Optional<VisitedMoveStopEntity> visitedMoveStopEntityOptional = visitedMoveStopRepository
                    .findVisitedMoveStopByMemberIdAndMoveStopEntityId(foundMember.getId(), foundMoveStopEntity.getId());

            if (visitedMoveStopEntityOptional.isEmpty()) {
                responses.add(new SearchVisitedMoveStopResponse(foundMoveStopEntity.getName(),
                        foundMoveStopEntity.getLatitude(), foundMoveStopEntity.getLongitude(), false));
            } else {
                VisitedMoveStopEntity visitedMoveStopEntity = visitedMoveStopEntityOptional.get();

                if (visitedMoveStopEntity.getDateOfLastVisit()
                        .plusMinutes(visitedMoveStopEntity.getMoveStopEntity().getCooldownTime()).isBefore(LocalDateTime.now())) {
                    responses.add(new SearchVisitedMoveStopResponse(foundMoveStopEntity.getName(),
                            foundMoveStopEntity.getLatitude(), foundMoveStopEntity.getLongitude(), false));
                } else {
                    responses.add(new SearchVisitedMoveStopResponse(foundMoveStopEntity.getName(),
                            foundMoveStopEntity.getLatitude(), foundMoveStopEntity.getLongitude(), true));
                }
            }
        }

        return responses;
    }
}
