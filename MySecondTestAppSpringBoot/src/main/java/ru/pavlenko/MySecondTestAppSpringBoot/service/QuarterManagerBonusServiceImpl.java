package ru.pavlenko.MySecondTestAppSpringBoot.service;

import ru.pavlenko.MySecondTestAppSpringBoot.model.Positions;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class QuarterManagerBonusServiceImpl implements QuarterManagerBonusService {
    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        if (!positions.isManager()){
            throw new IllegalArgumentException("Position is not manager");
        }

        LocalDate today = LocalDate.now();

        int month = today.getMonthValue();
        int quarter = (month - 1) / 3 + 1;

        int startMonth = (quarter - 1) * 3 + 1;
        LocalDate startOfQuarter = LocalDate.of(today.getYear(), startMonth, 1);

        LocalDate startOfNextQuarter = startOfQuarter.plusMonths(3);

        long daysInQuarter = ChronoUnit.DAYS.between(startOfQuarter, startOfNextQuarter);

        return salary * bonus * daysInQuarter * positions.getPositionCoefficient() / workDays;
    }
}
