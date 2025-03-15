package com.example.demo.shared;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public enum EResultStatus {
    // 200 為成功 OK
    SUCCESS(HttpStatus.OK, HttpStatus.OK.value(), HttpStatus.OK.name()),

    //100-511為 http 狀態碼
    // --- 4XX Client Error ---
    bad_request(HttpStatus.BAD_REQUEST),
    unauthorized(HttpStatus.UNAUTHORIZED),
    payment_required(HttpStatus.PAYMENT_REQUIRED),
    forbidden(HttpStatus.FORBIDDEN),
    not_found(HttpStatus.NOT_FOUND),
    method_not_allowed(HttpStatus.METHOD_NOT_ALLOWED),
    not_acceptable(HttpStatus.NOT_ACCEPTABLE),
    request_timeout(HttpStatus.REQUEST_TIMEOUT),
    conflict(HttpStatus.CONFLICT),
    gone(HttpStatus.GONE),
    length_required(HttpStatus.LENGTH_REQUIRED),

    // --- 5XX Server Error ---
    internal_server_error(HttpStatus.INTERNAL_SERVER_ERROR),
    not_implemented(HttpStatus.NOT_IMPLEMENTED),
    service_unavailable(HttpStatus.SERVICE_UNAVAILABLE)
    ;

    private HttpStatus status;
    private int code;
    private String message;

    EResultStatus(HttpStatus status) {
        this.status = status;
        this.code = status.value();
        this.message = status.name();
    }

    EResultStatus(HttpStatus status, String message) {
        this.status = status;
        this.code = status.value();
        this.message = message;
    }

    EResultStatus(HttpStatus status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
