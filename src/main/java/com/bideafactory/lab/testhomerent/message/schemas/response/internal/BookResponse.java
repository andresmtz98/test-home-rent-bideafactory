package com.bideafactory.lab.testhomerent.message.schemas.response.internal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class BookResponse extends Response {
    private Integer code;

    public BookResponse(@NotEmpty String message) {
        super(message);
    }

    public BookResponse(@NotEmpty String message, Integer code) {
        super(message);
        this.code = code;
    }
}
