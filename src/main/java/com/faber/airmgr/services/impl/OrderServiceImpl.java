package com.faber.airmgr.services.impl;

import com.faber.airmgr.data.entities.FlightEntity;
import com.faber.airmgr.data.entities.OrderEntity;
import com.faber.airmgr.data.enums.TypeFlight;
import com.faber.airmgr.data.repositories.impl.FlightRepositoryImpl;
import com.faber.airmgr.data.repositories.impl.OrderRepositoryImpl;

import com.faber.airmgr.models.FlightOrderModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl {

    @Autowired
    private OrderRepositoryImpl orderRepository;
    @Autowired
    private FlightRepositoryImpl flightRepository;

    public void doOrder(FlightOrderModel model) {
        try {
            FlightEntity flight = null;
//            FlightEntity flight = flightRepository.findById(model.getFlightId()).orElseThrow(() -> new RuntimeException("Flight notfound."));

            if (model.getNumOfAdults() == 0 && model.getNumOfSeniors() == 0 && model.getNumOfChildren() == 0) {
                throw new RuntimeException("Nums of order invalid.");
            }

            orderRepository.save(OrderEntity
                    .builder()
                    .flight(flight)
                    .adultsCount(model.getNumOfAdults())
                    .childrenCount(model.getNumOfChildren())
                    .seniorsCount(model.getNumOfSeniors())
                    .flightPrice(flight.getPrice())
                    .orderTime(new Date())
                    .returnType(TypeFlight.OneWay.toString().equals(model.getReturnType()) ? TypeFlight.OneWay : TypeFlight.RoundTrip)
                    .totalPrice(75 * flight.getPrice() * model.getNumOfChildren() + 85 * flight.getPrice() * model.getNumOfSeniors() + flight.getPrice() * model.getNumOfAdults())
                    .build());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException("Order failure !");
        }
    }

    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }
}
