package com.message.sender.service;

import com.message.sender.exception.ConditionsNotMetException;
import com.message.sender.model.Astronauts;
import com.message.sender.model.ResponseData;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotifierService {

    @Value("${api.url}")
    private String url;
    private final RestTemplate restTemplate;

    public ResponseData fetchData(){
        log.info("получение данных из API");
        var response = restTemplate.getForObject(url, Map.class);

        if (response == null) {
            throw new ConditionsNotMetException("полученный ответ от API пуст");
        }

        ResponseData responseData = new ResponseData();
        var astronauts = (List<Astronauts>) response.get("people");

        if (astronauts == null) {
            log.error("нет списка астронавтов");
            throw new ConditionsNotMetException("полученный ответ от API пуст");
        }

        responseData.setAstronauts(astronauts);
        responseData.setLocalTimeMessage(LocalDateTime.now());
        responseData.setPeopleCount(response.get("number").toString());

        return responseData;
    }
}