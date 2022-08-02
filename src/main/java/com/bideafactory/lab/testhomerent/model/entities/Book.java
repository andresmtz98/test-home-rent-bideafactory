package com.bideafactory.lab.testhomerent.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @Column
    private Long consecutive;

    @Column
    private String id;

    @Column
    private String name;

    @Column("lastname")
    private String lastName;

    @Column
    private Integer age;

    @Column("phone_number")
    private String phoneNumber;

    @Column("start_date")
    private String startDate;

    @Column("end_date")
    private String endDate;

    @Column("house_id")
    private String houseId;

    @Column("discount_code")
    private String discountCode;
}
