package ru.pavlenko.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.pavlenko.MySecondTestAppSpringBoot.model.Request;

@Service
public interface ModifyRequestService {

    void modify(Request request);
}
