package ru.pavlenko.MySecondTestAppSpringBoot.service;

import ru.pavlenko.MySecondTestAppSpringBoot.model.Positions;

import java.util.Calendar;
import java.util.Date;

public class AnnualBonusServiceImpl implements AnnualBonusService {
    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int numOfDays = cal.getActualMaximum(Calendar.DAY_OF_YEAR);

        return salary * bonus * numOfDays * positions.getPositionCoefficient() / workDays;
    }
}
