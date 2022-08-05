package com.akka.backendtest.exception;

import com.akka.backendtest.model.ResponseDataError;
import com.akka.backendtest.model.ResponseError;
import com.akka.backendtest.utils.UtilsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.ConstraintViolationException;
import java.net.SocketTimeoutException;
import java.util.List;

import static com.akka.backendtest.utils.Constants.LEVEL_WARN;

@ControllerAdvice
public class SimilarIdsExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ResponseError> handleNotFoundExecption(HttpClientErrorException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildResponseError(e.getStatusCode(), "No similar products found"));
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseError> handleNotFoundExecption(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildResponseError(HttpStatus.NOT_FOUND, "No similar products found"));
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseError> handleConstraintErrorException(ConstraintViolationException e){
        return ResponseEntity.badRequest().body(buildResponseError(HttpStatus.BAD_REQUEST,  "Path only allows numbers"));
    }

    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseError> handleMethodArgumentNotValidException(HttpRequestMethodNotSupportedException e){
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(buildResponseError(HttpStatus.METHOD_NOT_ALLOWED,  e.getMessage()));
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ResponseError> handleHttpServerErrorExceptionException(HttpServerErrorException e){
        return ResponseEntity.internalServerError().body(buildResponseError(e.getStatusCode(),  "Internal Error"));
    }

    @ExceptionHandler(SocketTimeoutException.class)
    public void handleHttpServerErrorExceptionException(SocketTimeoutException e){
        logWarn(e.getMessage());
    }

    private ResponseError buildResponseError(HttpStatus status, String description){
        return ResponseError.builder()
                    .errors(List.of(ResponseDataError.builder()
                            .code(status.toString())
                            .description(description)
                            .build()))
                    .build();
    }

    public void logWarn(String message) {
        UtilsLog.customLog(LEVEL_WARN, "Error: " +  message);
    }

}
