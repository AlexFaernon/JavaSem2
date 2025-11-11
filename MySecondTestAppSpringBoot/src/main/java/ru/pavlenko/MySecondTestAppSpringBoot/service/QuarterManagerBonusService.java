package ru.pavlenko.MySecondTestAppSpringBoot.service;

import ru.pavlenko.MySecondTestAppSpringBoot.model.Positions;

public interface QuarterManagerBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays);
}
