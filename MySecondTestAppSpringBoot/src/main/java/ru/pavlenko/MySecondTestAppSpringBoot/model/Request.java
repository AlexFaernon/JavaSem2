package ru.pavlenko.MySecondTestAppSpringBoot.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    /**Уникльаный идентификатор сообщения*/
    @NotBlank
    @Size(max = 32)
    private String uid;

    /**Уникльаный идентификатор операции*/
    @NotBlank
    @Size(max = 32)
    private String operationUid;

    /**Имя системы*/
    private String systemName;

    /**Время системы при запросе*/
    @NotBlank
    private String systemTime;

    /**Источник*/
    private String source;
    /**Позиция сотрудника*/
    private Positions positions;
    /**Зарплата сотрудника*/
    private Double salary;
    /**Бонус сотрудника*/
    private Double bonus;
    /**Кол-во рабочих дней сотрудника*/
    private Integer workDays;

    /**Уникльаный идентификатор передачи*/
    @Min(value = 1)
    @Max(value = 100000)
    private Integer communicationId;

    /**Уникльаный идентификатор шаблона*/
    private int templateId;
    /**Код продукта*/
    private int productCode;
    /**Код SMS*/
    private int smsCode;
}
