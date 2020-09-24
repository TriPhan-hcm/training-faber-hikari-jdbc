package com.faber.airmgr.services.impl;

import com.faber.airmgr.data.entities.FlightEntity;
import com.faber.airmgr.data.repositories.impl.AirPortRepositoryImpl;
import com.faber.airmgr.data.repositories.impl.FlightRepositoryImpl;
import com.faber.airmgr.models.AddFlightModel;
import com.faber.airmgr.models.FlightFilterModel;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Service
public class FlightServiceImpl {

    private static final Logger logger = LogManager.getLogger(FlightServiceImpl.class);

    @Autowired
    private FlightRepositoryImpl flightRepository;
    @Autowired
    private AirPortRepositoryImpl airPortRepository;

    @Cacheable("flights")
    public List<FlightEntity> filter(FlightFilterModel model) {
        try {
            List<FlightEntity> list = flightRepository.filter(model.getFromAirPort(), model.getToAirPort(), model.getDepartureDate(), model.getArrivalDate());
            System.out.println("Log Filter:...");
            logger.info(list);
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Cacheable("flights")
    public List<FlightEntity> findAll() {

        try {
            List<FlightEntity> list = flightRepository.findAll();
            System.out.println("Logg findAll:...");
            logger.info(list);
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Cacheable("flights")
    public FlightEntity findById(Long id) throws SQLException {
        try {
            return flightRepository.findById(id);
        } catch (Exception ex) {
            return null;
        }
    }

    public void add(AddFlightModel model) {
        try {
            FlightEntity addflight = new FlightEntity();

            addflight.setArrivalPort(model.getArrivalPort());
            addflight.setDeparturePort(model.getDeparturePort());
            addflight.setArrivalTime(model.getArrivalTime());
            addflight.setDepartureTime(model.getDepartureTime());
            addflight.setPrice(model.getPrice());

            flightRepository.save(addflight);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @CachePut("flights")
    public void update(AddFlightModel model) {
        try {
            FlightEntity updateFlight = new FlightEntity();

            updateFlight.setArrivalPort(model.getArrivalPort());
            updateFlight.setDeparturePort(model.getDeparturePort());
            updateFlight.setArrivalTime(model.getArrivalTime());
            updateFlight.setDepartureTime(model.getDepartureTime());
            updateFlight.setPrice(model.getPrice());
            updateFlight.setId(model.getId());

            flightRepository.edit(updateFlight);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @CacheEvict("flight")
    public void delete(Long id) {
        try {
            flightRepository.deleteById(id);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @CacheEvict(allEntries = true, value = {"flights"})
    @Scheduled(fixedDelay = 5000, initialDelay = 5000)
    public void clearCache() {
        System.out.println("-----> Cleared All Cache!");
    }
}
