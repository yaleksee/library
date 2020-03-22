package com.example.library.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), HttpStatus.NOT_FOUND, new Date());
        return new ResponseEntity<>(errorDetails, errorDetails.getHttpStatus());
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<?> resourceAccessException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), HttpStatus.CONFLICT, new Date());
        return new ResponseEntity<>(errorDetails, errorDetails.getHttpStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @Nonnull HttpHeaders headers,
                                                                  @Nonnull HttpStatus status,
                                                                  @Nonnull WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("status :", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors: ", errors);
        body.put("timestamp: ", new Date());
        body.put("fieldValidationError: ", ex.getBindingResult().getFieldErrors());
        return new ResponseEntity<>(body, headers, status);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                new Date());
        return new ResponseEntity<>(errorDetails, errorDetails.getHttpStatus());
    }

    //Аргумент метода не является ожидаемым типом:
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String message =
                ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ErrorDetails errorDetails =
                new ErrorDetails(message, HttpStatus.BAD_REQUEST, new Date());
        return new ResponseEntity<Object>(
                errorDetails, new HttpHeaders(), errorDetails.getHttpStatus());
    }


    @Data
    @AllArgsConstructor
    private static class ErrorDetails {
        private final String message;
        private final HttpStatus httpStatus;
        private final Date timestamp;
    }
}
