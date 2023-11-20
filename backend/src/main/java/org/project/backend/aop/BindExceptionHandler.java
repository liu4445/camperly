package org.project.backend.aop;

import lombok.extern.slf4j.Slf4j;
import org.project.backend.constant.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class BindExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity exception(MethodArgumentNotValidException ex) {
        StringBuilder error = new StringBuilder();
        error.append("예외 발생 필드 [");
        for (FieldError fieldError : ex.getFieldErrors()) {
            String format = String.format("필드명 : '%s', 입력값 : '%s'", fieldError.getField(), fieldError.getRejectedValue());
            log.error(format);
            error.append(format);
            error.append(" | ");
        }
        error.delete(error.length() - 3, error.length());
        error.append("]");

        ErrorMessage errorMessage = ErrorMessage.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(error.toString())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
