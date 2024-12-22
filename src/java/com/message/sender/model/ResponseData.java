package com.message.sender.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponseData {
    private LocalDateTime localTimeMessage;
    private String peopleCount;
    private List<Astronauts> astronauts;
}
