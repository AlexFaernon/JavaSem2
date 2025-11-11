package ru.pavlenko.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.pavlenko.MySecondTestAppSpringBoot.model.Positions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {

        // given
        Positions positions = Positions.HR;
        double bonus = 2.0;
        int workdays = 243;
        double salary = 100000.00;

        // when
        double result = new AnnualBonusServiceImpl().calculate(positions, salary, bonus, workdays);

        // then
        double expected = 360493.8271604938;
        assertThat(result).isEqualTo(expected);
    }
}