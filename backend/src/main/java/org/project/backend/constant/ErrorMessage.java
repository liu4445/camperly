package org.project.backend.constant;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorMessage {
    private HttpStatus httpStatus;
    private String error;
    private LocalDateTime timestamp;

    @Builder
    public ErrorMessage(HttpStatus httpStatus, String error, LocalDateTime timestamp) {
        this.httpStatus = httpStatus;
        this.error = error;
        this.timestamp = timestamp;
    }
}
