package ru.pavlenko.MyFourthTestAppSpringBoot.exception;

public class ValidationFailedException extends Exception {
    public ValidationFailedException(String message) { super(message); }
}
