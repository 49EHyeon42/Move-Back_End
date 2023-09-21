package dev.ehyeon.move.response;

import lombok.Getter;

@Getter
public class GetMoveStopResponse {

    private final String name;

    private final double latitude;

    private final double longitude;

    public GetMoveStopResponse(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
