package ru.pavlenko.MyThirdTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.pavlenko.MyThirdTestAppSpringBoot.exception.ValidationFailedException;

@Service
public class RequestValidationService implements ValidationService {

    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }
}