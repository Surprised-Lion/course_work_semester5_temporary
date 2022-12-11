package org.example.exceptions.exceptionHandlers;

import lombok.extern.slf4j.Slf4j;
import org.example.exceptions.AlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(Exception exception, WebRequest request) {
        return constructResponseEntity(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> nullPointer(Exception exception, WebRequest request) {
        return constructResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({AlreadyExistsException.class})
    public ResponseEntity<Object> alreadyUser(Exception exception, WebRequest request) {
        return constructResponseEntity(exception, HttpStatus.IM_USED);
    }

    private ResponseEntity<Object> constructResponseEntity(Exception exception, HttpStatus httpStatus) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("message", exception.getMessage());
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(body, httpStatus);
    }
}
