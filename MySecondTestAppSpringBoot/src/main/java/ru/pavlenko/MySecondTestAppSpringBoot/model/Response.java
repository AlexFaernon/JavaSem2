package ru.pavlenko.MySecondTestAppSpringBoot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    /**Уникльаный идентификатор сообщения*/
    private String uid;
    /**Уникльаный идентификатор операции*/
    private String operationUid;
    /**Время системы при ответе*/
    private String systemTime;
    /**Статус-код ответа*/
    private String code;
    /**Код ошибки*/
    private String errorCode;
    /**Сообщения ошибки*/
    private String errorMessage;
    /**Ежегодный бонус*/
    private double annualBonus;
}
