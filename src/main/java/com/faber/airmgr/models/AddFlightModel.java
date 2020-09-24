package com.faber.airmgr.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
public class AddFlightModel {
    private Long id;
    private Long departurePort;

    private Long arrivalPort;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date departureTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date arrivalTime;

    private Long price;
}
