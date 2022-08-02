package com.bideafactory.lab.testhomerent.message.schemas.response.discountapi;

import lombok.Data;

@Data
public class NewResponse {
    private Boolean status;
    private String id;
    private String userId;
    private String houseId;
    private String discountCode;
}
