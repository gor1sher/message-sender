package com.message.sender.service;

import com.message.sender.model.ResponseData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitService {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange = "exchange";
    private final String routingKey = "key";

    public void sendMessage(ResponseData responseData){
        log.info("отправлено сообщение: {}", responseData.getLocalTimeMessage() + " количество людей на борту: " +
                responseData.getPeopleCount());

        rabbitTemplate.convertAndSend(exchange, routingKey, responseData);
    }
}
