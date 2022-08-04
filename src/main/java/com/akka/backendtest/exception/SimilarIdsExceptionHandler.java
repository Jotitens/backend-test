package com.akka.backendtest.exception;

import com.akka.backendtest.model.ResponseDataError;
import com.akka.backendtest.model.ResponseError;
import com.akka.backendtest.utils.UtilsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static com.akka.backendtest.utils.Constants.LEVEL_ERROR;

@ControllerAdvice
public class SimilarIdsExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ResponseError> handleNotFoundExecption(HttpClientErrorException e){
        logError(e.getStatusCode().toString(), e.getMessage());
        return buildResponseError(e.getStatusCode(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseError> handleConstraintErrorException(ConstraintViolationException e){
        logError(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
        return buildResponseError(HttpStatus.BAD_REQUEST,  "Path only allows numbers");
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        logError(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
        return buildResponseError(HttpStatus.BAD_REQUEST,  e.getMessage());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ResponseError> handleHttpServerErrorExceptionException(HttpServerErrorException e){
        logError(e.getStatusCode().toString(), e.getMessage());
        return buildResponseError(e.getStatusCode(),  e.getMessage());
    }



    private ResponseEntity<ResponseError> buildResponseError(HttpStatus status, String description){
        return ResponseEntity.of((
                Optional.of(
                        ResponseError.builder()
                                .errors(List.of(ResponseDataError.builder()
                                        .code(status.toString())
                                        .description(description)
                                        .build()))
                                .build())));

    }

    public void logError(String code, String message) {
        UtilsLog.customLog(LEVEL_ERROR, "Error: " + code +  " / " + message);
    }
}
