package ru.pavlenko.MyThirdTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.pavlenko.MyThirdTestAppSpringBoot.model.Response;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}
