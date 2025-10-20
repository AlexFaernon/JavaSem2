package ru.pavlenko.MyThirdTestAppSpringBoot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.pavlenko.MyThirdTestAppSpringBoot.model.Response;

import java.util.UUID;

@Slf4j
@Service
@Qualifier("ModifyOperationUidInResponseService")
public class ModifyOperationUidInResponseService implements ModifyResponseService {

    @Override
    public Response modify(Response response) {
        UUID uuid = UUID.randomUUID();
        response.setOperationUid(uuid.toString());
        log.info("UUID ответа: {}", uuid);
        return response;
    }
}
