package com.felps.genericorderedinnerbuilder.builder;

import com.felps.genericorderedinnerbuilder.entity.TestClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TestBuilderTest {

    private static final LocalDate NOW = LocalDate.now();

    @Test
    void shouldCreateAnInstanceOfTestClass() {
        TestClass test = TestBuilder.builder()
                .name("Test")
                .value(1L)
                .startDate(NOW)
                .endDate(NOW.plusDays(1))
                .build();

        assertEquals("Test", test.getName());
        assertEquals(Long.valueOf(1L), test.getValue());
        assertEquals(NOW, test.getStartDate());
        assertEquals(NOW.plusDays(1), test.getEndDate());
    }

    @Test
    void shouldThrowExceptionWhenEndDateIsBeforeStartDate() {
        Executable executable = () -> TestBuilder.builder()
                .name("Test")
                .value(1L)
                .startDate(NOW)
                .endDate(NOW.minusDays(1))
                .build();
        assertEquals("End date is greater then start date", assertThrows(IllegalArgumentException.class, executable).getMessage());
    }
}