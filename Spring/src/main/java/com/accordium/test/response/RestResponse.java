package com.accordium.test.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> {
    public static final String STATUS_OK = "OK";
    public static final String STATUS_FAILED = "NOK";

    private String status = STATUS_OK;

    private String message;

    private T data;

    public RestResponse() {

    }

    public RestResponse(String status) {
        this.status = status;
    }

    public RestResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public RestResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public RestResponse(String status, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}