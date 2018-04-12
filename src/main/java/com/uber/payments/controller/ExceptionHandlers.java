package com.uber.payments.controller;

import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.uber.payments.dto.ExceptionResponse;

@ControllerAdvice
public class ExceptionHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlers.class);

    /**
     * Handles exception thrown by spring while binding requestBody to parameters of controller methods
     *
     * @param methodArgumentNotValidException exception thrown by spring while binding request body to requestMapping method
     * @return ExceptionResponse with the reason as errorMessage and constraints violated as errors list
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse constraintViolationExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        ExceptionResponse exceptionResponse = logAndBuildExceptionResponse("Invalid values are passed.", HttpStatus.BAD_REQUEST,
                methodArgumentNotValidException);
        exceptionResponse.setErrors(methodArgumentNotValidException.getBindingResult().getAllErrors().stream().
                map(e -> {
                    if (e instanceof FieldError)
                        return ((FieldError) e).getField() + " " + e.getDefaultMessage();
                    else
                        return e.getDefaultMessage();
                }).collect(Collectors.toList()));
        return exceptionResponse;
    }

    /**
     * Thrown when enum cannot be bound to params.
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse messageNotReadableException(HttpMessageNotReadableException exception) {
        ExceptionResponse exceptionResponse = logAndBuildExceptionResponse("Invalid values are passed.", HttpStatus.BAD_REQUEST,
                exception);
        exceptionResponse.setMessage(exception.getMessage());
        return exceptionResponse;

    }


    private ExceptionResponse logAndBuildExceptionResponse(String msg, HttpStatus httpStatus, Exception ex) {
        ExceptionResponse exceptionResponse = buildExceptionResponse(msg, httpStatus);
        StringBuilder strBuilder = new StringBuilder(msg);
        strBuilder.append(", UUID :");
        strBuilder.append(exceptionResponse.getError_uuid());
        LOGGER.error(strBuilder.toString(), ex);
        return exceptionResponse;
    }

    private ExceptionResponse buildExceptionResponse(String errorMessage, HttpStatus httpStatus) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(errorMessage);
        exceptionResponse.setHttp_status_code(httpStatus.value());
        String uuid = UUID.randomUUID().toString();
        exceptionResponse.setError_uuid(uuid);
        return exceptionResponse;
    }
}
