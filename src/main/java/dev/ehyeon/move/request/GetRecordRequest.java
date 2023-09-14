package dev.ehyeon.move.request;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class GetRecordRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime from;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime to;

    public GetRecordRequest(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }
}
