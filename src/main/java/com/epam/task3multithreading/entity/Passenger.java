package com.epam.task3multithreading.entity;

import com.epam.task3multithreading.service.TaxiStation;
import com.epam.task3multithreading.service.TaxiStationException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Passenger implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(Passenger.class);
    @JsonProperty
    private String name;
    @JsonProperty
    private boolean isArrived;
    private final TaxiStation taxiStation = TaxiStation.getInstance();

    public Passenger() {
        //  Constructor is empty because objects parsing and creating from JSON
    }

    public String getName() {
        return name;
    }

    public boolean getIsArrived() {
        return isArrived;
    }

    void changeIsArrived() {
        isArrived = true;
    }

    @Override
    public void run() {
        try {
            Taxi taxi = taxiStation.orderTaxi();
            taxi.transfer(this);
            taxiStation.releaseTaxi(taxi);
            LOGGER.info("Taxi is free");
        } catch (TaxiStationException e) {
            LOGGER.error(e.getMessage(), e.fillInStackTrace());
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Passenger)) {
            return false;
        }
        Passenger passenger = (Passenger) object;
        return isArrived == passenger.isArrived && Objects.equals(name, passenger.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isArrived);
    }

    @Override
    public String toString() {
        return "\nPassenger: " + "name = '" + name + "', " + "isArrived = " + isArrived;
    }
}
