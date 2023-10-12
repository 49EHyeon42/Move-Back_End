package dev.ehyeon.move.record.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SearchTotalRecordResponse {

    private int totalMileage;
    private double totalTravelDistance;
    private double totalStep;
}
