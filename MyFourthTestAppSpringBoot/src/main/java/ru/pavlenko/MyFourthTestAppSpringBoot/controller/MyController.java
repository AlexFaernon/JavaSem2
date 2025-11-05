package ru.pavlenko.MyFourthTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.pavlenko.MyFourthTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.pavlenko.MyFourthTestAppSpringBoot.exception.ValidationFailedException;
import ru.pavlenko.MyFourthTestAppSpringBoot.model.Request;
import ru.pavlenko.MyFourthTestAppSpringBoot.model.Response;
import ru.pavlenko.MyFourthTestAppSpringBoot.service.ValidationService;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;

    @Autowired
    public MyController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {

        Instant now = Instant.now();
        Instant start = safeParseInstant(request.getSystemTime());

        if (start != null) {
            long ms = Duration.between(start, now).toMillis();
            log.info("S2 transit time: {} ms | start={}, now={}", ms, start, now);
        } else {
            log.warn("S2 transit time: cannot compute - missing/invalid timestamp (systemTime='{}')",
                    request.getSystemTime());
        }

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(request.getSystemTime())
                .code("success")
                .errorCode("")
                .errorMessage("")
                .build();

        try {
            validationService.isValid(bindingResult);

            if ("123".equals(request.getUid())) {
                throw new UnsupportedCodeException("UID 123 не поддерживается");
            }
        } catch (ValidationFailedException e) {
            response.setCode("failed");
            response.setErrorCode("ValidationException");
            response.setErrorMessage("Ошибка валидации");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            response.setCode("failed");
            response.setErrorCode("UnsupportedCodeException");
            response.setErrorMessage("uid 123");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode("failed");
            response.setErrorCode("UnknownException");
            response.setErrorMessage("Произошла непредвиденная ошибка");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Instant safeParseInstant(String iso) {
        if (iso == null || iso.isBlank()) return null;
        try {
            return Instant.parse(iso);
        } catch (Exception ignored) {
            return null;
        }
    }
}
