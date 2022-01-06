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
    private DisLocation location;
    @JsonProperty
    private DisLocation destination;
    @JsonProperty
    private boolean isArrived;
    private final TaxiStation taxiStation = TaxiStation.getInstance();

    public Passenger() {
        //  Constructor is empty because objects parsing and creating from JSON
    }

    public DisLocation getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public boolean getIsArrived() {
        return isArrived;
    }

    public DisLocation getDestination() {
        return destination;
    }

    public void setArrived(boolean arrived) {
        isArrived = arrived;
    }

    @Override
    public void run() {
        LOGGER.info("Start thread");
        try {
            Taxi taxi = taxiStation.orderTaxi();
            LOGGER.info("Try to transfer passenger");
            taxi.transfer(this);
            LOGGER.info("Passenger transferred");
            taxiStation.releaseTaxi(taxi);
        } catch (TaxiStationException e) {
            LOGGER.error(e.getMessage());
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
        return isArrived == passenger.isArrived && Objects.equals(name, passenger.name)
                && Objects.equals(location, passenger.location) && Objects.equals(destination, passenger.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, destination, isArrived);
    }

    @Override
    public String toString() {
        return "\nPassenger: " + "name = '" + name + "', " + "Passenger" + location + ", "
                + "Passenger destination:" + destination + ", isArrived = " + isArrived;
    }
}
