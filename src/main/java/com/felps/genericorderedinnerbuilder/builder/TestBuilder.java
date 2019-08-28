package com.felps.genericorderedinnerbuilder.builder;

import com.felps.genericorderedinnerbuilder.entity.TestClass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter(AccessLevel.PRIVATE)
public class TestBuilder implements ITestBuilder {

    private String name;

    private Long value;

    private LocalDate startDate;

    private LocalDate endDate;

    public static TestBuilder builder() {
        return new TestBuilder();
    }

    @Override
    public Name name(String name) {
        setName(name);
        return new NameBuilder();
    }

    @Override
    public Value value(Long value) {
        setValue(value);
        return new ValueBuilder();
    }

    private TestBuilder getInstance() {
        return this;
    }

    private class NameBuilder implements ITestBuilder.Name {

        @Override
        public Value value(Long value) {
            setValue(value);
            return new ValueBuilder();
        }

        @Override
        public StartDate startDate(LocalDate startDate) {
            setStartDate(startDate);
            return new StartDateBuilder();
        }
    }

    private class ValueBuilder implements ITestBuilder.Value {

        @Override
        public StartDate startDate(LocalDate startDate) {
            setStartDate(startDate);
            return new StartDateBuilder();
        }
    }

    private class StartDateBuilder implements ITestBuilder.StartDate {

        @Override
        public Builder endDate(LocalDate endDate) {
            if (isBeforeStartDate(getStartDate()).test(endDate)) {
                throw new IllegalArgumentException("End date is greater then start date");
            }
            setEndDate(endDate);
            return new Builder();
        }
    }

    private class Builder implements ITestBuilder.Build {

        @Override
        public TestClass build() {
            TestBuilder builder = getInstance();
            return new TestClass(builder);
        }
    }
}

