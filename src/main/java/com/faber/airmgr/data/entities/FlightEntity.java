package com.faber.airmgr.data.entities;
import java.io.Serializable;
import java.util.Date;


public class FlightEntity implements Serializable{
    private Long id;

    private Long departurePort;

    private Long arrivalPort;

    private Date departureTime;

    private Date arrivalTime;

    private Long price;

    public FlightEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeparturePort() {
        return departurePort;
    }

    public void setDeparturePort(Long departurePort) {
        this.departurePort = departurePort;
    }

    public Long getArrivalPort() {
        return arrivalPort;
    }

    public void setArrivalPort(Long arrivalPort) {
        this.arrivalPort = arrivalPort;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
    
    
}
