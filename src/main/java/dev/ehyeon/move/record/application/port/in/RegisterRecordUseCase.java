package dev.ehyeon.move.record.application.port.in;

public interface RegisterRecordUseCase {

    RegisterRecordResponse registerRecord(String memberEmail, RegisterRecordRequest request);
}
