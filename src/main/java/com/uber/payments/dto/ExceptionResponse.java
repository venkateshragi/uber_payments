package com.uber.payments.dto;

import java.util.List;

/**
 * Created by arvetic on 14/07/17.
 */

public class ExceptionResponse {

    String message;
    int http_status_code;
    String error_uuid;
    List<String> errors;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHttp_status_code() {
        return http_status_code;
    }

    public void setHttp_status_code(int http_status_code) {
        this.http_status_code = http_status_code;
    }

    public String getError_uuid() {
        return error_uuid;
    }

    public void setError_uuid(String error_uuid) {
        this.error_uuid = error_uuid;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
