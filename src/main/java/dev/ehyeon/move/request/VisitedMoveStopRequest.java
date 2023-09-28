//package dev.ehyeon.move.request;
//
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Getter
//public class VisitedMoveStopRequest {
//
//    @NotBlank
//    private String email;
//
//    @NotBlank
//    private String address;
//
//    @NotNull
//    private Double latitude;
//
//    @NotNull
//    private Double longitude;
//
//    // TODO refactor
//    public VisitedMoveStopRequest(String email, String address, Double latitude, Double longitude) {
//        this.email = email;
//        this.address = address;
//        this.latitude = latitude;
//        this.longitude = longitude;
//    }
//}
