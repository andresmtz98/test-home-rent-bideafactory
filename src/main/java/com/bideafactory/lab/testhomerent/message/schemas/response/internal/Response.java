package com.bideafactory.lab.testhomerent.message.schemas.response.internal;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class Response {

    @NotEmpty
    private String message;
}
