package com.bideafactory.lab.testhomerent.message.schemas.request.internal;

import com.bideafactory.lab.testhomerent.util.Constant;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data @NotNull public class BookRequest {

    @NotEmpty @Size(min = Constant.INT_NUMBER_NINE, max = Constant.INT_NUMBER_TEN) private String id;

    @NotEmpty @Size(min = Constant.INT_NUMBER_TWO, max = Constant.INT_NUMBER_FIFTY) private String name;

    @JsonProperty("lastname") @NotEmpty @Size(min = Constant.INT_NUMBER_TWO, max = Constant.INT_NUMBER_FIFTY) private String lastName;

    @Min(Constant.LONG_NUMBER_EIGHTEEN) @Max(Constant.LONG_NUMBER_ONE_HUNDRED) @NotNull private Integer age;

    @NotEmpty @Size(min = Constant.INT_NUMBER_NINE, max = Constant.INT_NUMBER_TWENTY) private String phoneNumber;

    @NotEmpty private String startDate;

    @NotEmpty private String endDate;

    @NotEmpty @Size(min = Constant.INT_NUMBER_SIX, max = Constant.INT_NUMBER_FIFTEEN) private String houseId;

    @Size(min = Constant.INT_NUMBER_EIGHT, max = Constant.INT_NUMBER_EIGHT) private String discountCode;
}
