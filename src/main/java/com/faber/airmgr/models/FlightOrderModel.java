package com.faber.airmgr.models;

public class FlightOrderModel {
    private Long flightId;
    private Integer numOfAdults ;
    private Integer numOfChildren ;
    private Integer numOfSeniors ;
    private String returnType;

    public FlightOrderModel() {
    }

    public FlightOrderModel(Long flightId, Integer numOfAdults, Integer numOfChildren, Integer numOfSeniors, String returnType) {
        this.flightId = flightId;
        this.numOfAdults = numOfAdults;
        this.numOfChildren = numOfChildren;
        this.numOfSeniors = numOfSeniors;
        this.returnType = returnType;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Integer getNumOfAdults() {
        return numOfAdults;
    }

    public void setNumOfAdults(Integer numOfAdults) {
        this.numOfAdults = numOfAdults;
    }

    public Integer getNumOfChildren() {
        return numOfChildren;
    }

    public void setNumOfChildren(Integer numOfChildren) {
        this.numOfChildren = numOfChildren;
    }

    public Integer getNumOfSeniors() {
        return numOfSeniors;
    }

    public void setNumOfSeniors(Integer numOfSeniors) {
        this.numOfSeniors = numOfSeniors;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
    
    
}
