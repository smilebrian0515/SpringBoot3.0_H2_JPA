package com.example.demo.shared;

import com.example.demo.exception.RootException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Slf4j
public class ApiResponse {
    private Integer status;
    private Integer code;
    private String message;
    private Object data = null;
    private String token;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public ApiResponse() {
        timestamp = LocalDateTime.now();
    }

    public static ApiResponse newInstance() {
        return new ApiResponse();
    }

    public ApiResponse logError(String message, Exception e) {
        log.error(message, e);
        return this;
    }

    public ApiResponse logWarn(String message, Exception e) {
        log.warn(message, e);
        return this;
    }

    public ResponseEntity<ApiResponse> success(Object data) {
        log.info(data.toString());
        ApiResponse ret = new ApiResponse()
                .resultStatus(EResultStatus.SUCCESS)
                .code(0)
                .data(data);
        return new ResponseEntity<>(ret, EResultStatus.SUCCESS.getStatus());
    }

    public ResponseEntity<ApiResponse> success(Object data, String token) {
        log.info(data.toString());
        ApiResponse ret = new ApiResponse()
                .resultStatus(EResultStatus.SUCCESS)
                .code(0)
                .data(data)
                .token(token);
        return new ResponseEntity<>(ret, EResultStatus.SUCCESS.getStatus());
    }

    public ResponseEntity<ApiResponse> resourceNotFound() {
        ApiResponse ret = newInstance().resultStatus(EResultStatus.not_found).data(null);
        return new ResponseEntity<>(ret, EResultStatus.not_found.getStatus());
    }

    public ResponseEntity<ApiResponse> resourceNotFound(String message) {
        ApiResponse ret = newInstance().resultStatus(EResultStatus.not_found).message(message).data(null);
        return new ResponseEntity<>(ret, EResultStatus.not_found.getStatus());
    }

    public ResponseEntity<ApiResponse> error(EResultStatus resultStatus, RootException e) {
        log.error(resultStatus.toString(), e);
        return error(resultStatus, e.getCode(), e.getMessage());
    }

    public ResponseEntity<ApiResponse> error(EResultStatus resultStatus, int code, String message) {
        ApiResponse ret = newInstance()
                .resultStatus(resultStatus)
                .code(code)
                .message(message)
                .data(null);
        return new ResponseEntity<>(ret, resultStatus.getStatus());
    }

    public ResponseEntity<ApiResponse> error(EResultStatus resultStatus, int code, String message, String token) {
        ApiResponse ret = newInstance()
                .resultStatus(resultStatus)
                .code(code)
                .message(message)
                .token(token)
                .data(null);
        return new ResponseEntity<>(ret, resultStatus.getStatus());
    }

    public ApiResponse resultStatus(EResultStatus resultStatus) {
        this.status = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        return this;
    }

    public ApiResponse status(Integer status) {
        this.status = status;
        return this;
    }

    public ApiResponse code(Integer code) {
        this.code = code;
        return this;
    }

    public ApiResponse message(String message) {
        this.message = message;
        return this;
    }

    public ApiResponse token(String token) {
        this.token = token;
        return this;
    }

    public ApiResponse data(Object data) {
        this.data = data;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public Object getData() {
        return data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "status=" + status +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", token='" + token + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
