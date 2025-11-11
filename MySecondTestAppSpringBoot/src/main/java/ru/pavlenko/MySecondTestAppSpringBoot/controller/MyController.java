package ru.pavlenko.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.pavlenko.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.pavlenko.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.pavlenko.MySecondTestAppSpringBoot.model.Request;
import ru.pavlenko.MySecondTestAppSpringBoot.model.Response;
import ru.pavlenko.MySecondTestAppSpringBoot.service.AnnualBonusService;
import ru.pavlenko.MySecondTestAppSpringBoot.service.ModifyRequestService;
import ru.pavlenko.MySecondTestAppSpringBoot.service.ValidationService;
import ru.pavlenko.MySecondTestAppSpringBoot.util.DateTimeUtil;

import java.time.Instant;
import java.util.Date;

@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyRequestService modifyRequestService;
    private final AnnualBonusService annualBonusService;

    @Autowired
    public MyController(ValidationService validationService,
                        ModifyRequestService modifyRequestService,
                        AnnualBonusService annualBonusService) {
        this.validationService = validationService;
        this.modifyRequestService = modifyRequestService;
        this.annualBonusService = annualBonusService;
    }

    @PostMapping("/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {

        request.setSystemTime(Instant.now().toString());

        try {
            validationService.isValid(bindingResult);

            if ("123".equals(request.getUid())) {
                throw new UnsupportedCodeException("UID 123 не поддерживается");
            }

            double annualBonus = annualBonusService.calculate(
                    request.getPositions(),
                    request.getSalary(),
                    request.getBonus(),
                    request.getWorkDays()
            );

            modifyRequestService.modify(request);

            Response response = Response.builder()
                    .uid(request.getUid())
                    .operationUid(request.getOperationUid())
                    .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                    .code("success")
                    .annualBonus(annualBonus)
                    .build();

            return ResponseEntity.ok(response);

        } catch (ValidationFailedException e) {
            return buildErrorResponse("ValidationException", "Ошибка валидации", HttpStatus.BAD_REQUEST, request);
        } catch (UnsupportedCodeException e) {
            return buildErrorResponse("UnsupportedCodeException", "uid 123", HttpStatus.BAD_REQUEST, request);
        } catch (Exception e) {
            return buildErrorResponse("UnknownException", "Произошла непредвиденная ошибка", HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
    }

    private ResponseEntity<Response> buildErrorResponse(String errorCode, String message, HttpStatus status, Request request) {
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code("failed")
                .errorCode(errorCode)
                .errorMessage(message)
                .build();

        return new ResponseEntity<>(response, status);
    }
}
