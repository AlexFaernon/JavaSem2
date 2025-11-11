package ru.pavlenko.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.pavlenko.MySecondTestAppSpringBoot.model.Positions;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class QuarterManagerBonusServiceImplTest {
    private final QuarterManagerBonusServiceImpl calculator = new QuarterManagerBonusServiceImpl();

    @Test
    void testCalculateForManager() {
        // given
        Positions position = Positions.TL;
        double salary = 1000;
        double bonus = 1.5;
        int workDays = 20;

        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int quarter = (month - 1) / 3 + 1;
        int startMonth = (quarter - 1) * 3 + 1;
        LocalDate startOfQuarter = LocalDate.of(today.getYear(), startMonth, 1);
        LocalDate startOfNextQuarter = startOfQuarter.plusMonths(3);
        long daysInQuarter = ChronoUnit.DAYS.between(startOfQuarter, startOfNextQuarter);

        double expected = salary * bonus * daysInQuarter * position.getPositionCoefficient() / workDays;

        // when
        double actual = calculator.calculate(position, salary, bonus, workDays);

        // then
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    void testCalculateThrowsExceptionForNonManager() {
        Positions nonManager = Positions.DEV;

        assertThrows(IllegalArgumentException.class, () ->
                        calculator.calculate(nonManager, 1000, 1.5, 20));
    }
}