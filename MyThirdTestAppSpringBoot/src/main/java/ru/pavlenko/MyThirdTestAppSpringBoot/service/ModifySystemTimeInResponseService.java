package ru.pavlenko.MyThirdTestAppSpringBoot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.pavlenko.MyThirdTestAppSpringBoot.model.Response;
import ru.pavlenko.MyThirdTestAppSpringBoot.util.DateTimeUtil;

import java.util.Date;

@Slf4j
@Service
@Qualifier("ModifySystemTimeInResponseService")
public class ModifySystemTimeInResponseService implements ModifyResponseService {

    @Override
    public Response modify(Response response) {
        var formattedTime = DateTimeUtil.getCustomFormat().format(new Date());
        response.setSystemTime(formattedTime);
        log.info("Время ответа: {}", formattedTime);
        return response;
    }
}