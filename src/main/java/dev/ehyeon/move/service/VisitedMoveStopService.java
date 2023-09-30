//package dev.ehyeon.move.service;
//
//import dev.ehyeon.move.entity.Member;
//import dev.ehyeon.move.move_stop.adapter.out.persistence.MoveStopEntity;
//import dev.ehyeon.move.entity.VisitedMoveStop;
//import dev.ehyeon.move.repository.MemberRepository;
//import dev.ehyeon.move.move_stop.adapter.out.persistence.MoveStopRepository;
//import dev.ehyeon.move.repository.VisitedMoveStopRepository;
//import dev.ehyeon.move.request.VisitedMoveStopRequest;
//import dev.ehyeon.move.security.exception.MemberNotFoundException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class VisitedMoveStopService {
//
//    private final MemberRepository memberRepository;
//
//    private final MoveStopRepository moveStopRepository;
//
//    private final VisitedMoveStopRepository visitedMoveStopRepository;
//
//    // TODO refactor
//    public void saveOrUpdateVisitedMoveStop(VisitedMoveStopRequest request) {
//        Member foundMember = memberRepository.findMemberByEmail(request.getEmail())
//                .orElseThrow(MemberNotFoundException::new);
//
//        List<MoveStopEntity> foundMoveStopEntities = moveStopRepository.findMoveStopByAddress(request.getAddress());
//
//        for (MoveStopEntity foundMoveStopEntity : foundMoveStopEntities) {
//            double distance = getDistanceInMeter(request.getLatitude(), request.getLongitude(), foundMoveStopEntity.getLatitude(), foundMoveStopEntity.getLongitude());
//
//            if (distance <= 30) {
//                Optional<VisitedMoveStop> visitedMoveStopOptional = visitedMoveStopRepository
//                        .findVisitedMoveStopByMemberIdAndMoveStopId(foundMember.getId(), foundMoveStopEntity.getId());
//
//                if (visitedMoveStopOptional.isPresent()) { // 방문 기록이 있다면
//                    LocalDateTime dateOfLastVisit = visitedMoveStopOptional.get().getDateOfLastVisit();
//
//                    if (dateOfLastVisit.plusHours(1).isBefore(LocalDateTime.now())) {
//                        log.info("사용자: " + request.getEmail() + ", 마지막 방문 + 1시간 이후 재방문: " + foundMoveStopEntity.getName());
//
//                        visitedMoveStopOptional.get().updateDateOfLastVisit(LocalDateTime.now());
//                    } else {
//                        log.info("사용자: " + request.getEmail() + ", 방문: " + foundMoveStopEntity.getName() + ", 등록 안됨");
//                    }
//                } else { // 첫 방문
//                    log.info("사용자: " + request.getEmail() + ", 첫 방문: " + foundMoveStopEntity.getName());
//
//                    visitedMoveStopRepository.save(new VisitedMoveStop(foundMember, foundMoveStopEntity, LocalDateTime.now()));
//                }
//            } else {
//                log.info(foundMoveStopEntity.getName() + ", distance:" + distance);
//            }
//        }
//    }
//
//    private double getDistanceInMeter(double latitude1, double longitude1, double latitude2, double longitude2) {
//        return (radianToDegree(Math.acos(Math.sin(degreeToRadian(latitude1)) * Math.sin(degreeToRadian(latitude2)) +
//                Math.cos(degreeToRadian(latitude1)) * Math.cos(degreeToRadian(latitude2)) * Math.cos(degreeToRadian(longitude1 - longitude2)))) *
//                60 * 1.1515) * 1609.344;
//
//    }
//
//    private double degreeToRadian(double degree) {
//        return (degree * Math.PI / 180.0);
//    }
//
//    private double radianToDegree(double radian) {
//        return (radian * 180 / Math.PI);
//    }
//}