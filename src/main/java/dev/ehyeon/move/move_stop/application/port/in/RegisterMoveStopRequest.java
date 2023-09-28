package dev.ehyeon.move.move_stop.application.port.in;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class RegisterMoveStopRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;
}
