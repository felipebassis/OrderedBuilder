package com.felps.genericorderedinnerbuilder.builder;

import com.felps.genericorderedinnerbuilder.entity.TestClass;

import java.time.LocalDate;
import java.util.function.Predicate;

public interface ITestBuilder {

    ITestBuilder.Name name(String name);

    ITestBuilder.Value value(Long value);

    interface Name {
        ITestBuilder.Value value(Long value);

        ITestBuilder.StartDate startDate(LocalDate startDate);
    }

    interface Value {
        ITestBuilder.StartDate startDate(LocalDate startDate);
    }

    interface StartDate {
        ITestBuilder.Build endDate(LocalDate endDate);

        default Predicate<LocalDate> isBeforeStartDate(LocalDate startDate) {
            return endDate -> endDate.isBefore(startDate);
        }
    }

    interface Build extends OrderedBuilder<TestClass> {
    }
}
