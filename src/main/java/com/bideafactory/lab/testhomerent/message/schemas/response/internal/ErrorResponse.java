package com.bideafactory.lab.testhomerent.message.schemas.response.internal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class ErrorResponse extends Response {
    private Integer statusCode;
    private String error;

    public ErrorResponse(@NotEmpty String message) {
        super(message);
    }

    public ErrorResponse(@NotEmpty String message, Integer statusCode,
        String error) {
        super(message);
        this.statusCode = statusCode;
        this.error = error;
    }
}
