package com.felps.genericorderedinnerbuilder.entity;

import com.felps.genericorderedinnerbuilder.builder.TestBuilder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
public class TestClass {

    private String name;

    private Long value;

    private LocalDate startDate;

    private LocalDate endDate;

    public TestClass (TestBuilder builder) {
         this.name = builder.getName();
         this.value = builder.getValue();
         this.startDate = builder.getStartDate();
         this.endDate = builder.getEndDate();
    }
}
