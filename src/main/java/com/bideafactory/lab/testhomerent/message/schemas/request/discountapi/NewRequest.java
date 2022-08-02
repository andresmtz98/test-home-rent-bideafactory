package com.bideafactory.lab.testhomerent.message.schemas.request.discountapi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewRequest {
    private String userId;
    private String houseId;
    private String discountCode;
}
