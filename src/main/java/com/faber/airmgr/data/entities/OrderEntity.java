package com.faber.airmgr.data.entities;
import com.faber.airmgr.data.enums.TypeFlight;
import java.io.Serializable;
import lombok.Builder;
import java.util.Date;

@Builder
public class OrderEntity implements Serializable{
    private Long id;

    private Date orderTime;

    private TypeFlight returnType;

    private FlightEntity flight;

    private Integer adultsCount;

    private Integer seniorsCount;

    private Integer childrenCount;

    private Long flightPrice;

    private Long totalPrice;

    public Integer totalTickets() {
        return adultsCount + seniorsCount + childrenCount;
    }

    public OrderEntity() {
    }

    public OrderEntity(Long id, Date orderTime, TypeFlight returnType, FlightEntity flight, Integer adultsCount, Integer seniorsCount, Integer childrenCount, Long flightPrice, Long totalPrice) {
        this.id = id;
        this.orderTime = orderTime;
        this.returnType = returnType;
        this.flight = flight;
        this.adultsCount = adultsCount;
        this.seniorsCount = seniorsCount;
        this.childrenCount = childrenCount;
        this.flightPrice = flightPrice;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public TypeFlight getReturnType() {
        return returnType;
    }

    public void setReturnType(TypeFlight returnType) {
        this.returnType = returnType;
    }

    public FlightEntity getFlight() {
        return flight;
    }

    public void setFlight(FlightEntity flight) {
        this.flight = flight;
    }

    public Integer getAdultsCount() {
        return adultsCount;
    }

    public void setAdultsCount(Integer adultsCount) {
        this.adultsCount = adultsCount;
    }

    public Integer getSeniorsCount() {
        return seniorsCount;
    }

    public void setSeniorsCount(Integer seniorsCount) {
        this.seniorsCount = seniorsCount;
    }

    public Integer getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(Integer childrenCount) {
        this.childrenCount = childrenCount;
    }

    public Long getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(Long flightPrice) {
        this.flightPrice = flightPrice;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
    
}
