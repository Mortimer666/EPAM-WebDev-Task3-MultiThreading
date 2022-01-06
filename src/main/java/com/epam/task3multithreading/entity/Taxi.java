package com.epam.task3multithreading.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Taxi {
    private static final Logger LOGGER = LogManager.getLogger(Taxi.class);
    private DisLocation taxiLocation;
    private final long taxiId;
    private final double taxiRadius;

    public Taxi(DisLocation taxiLocation, long taxiId, double taxiRadius) {
        this.taxiLocation = taxiLocation;
        this.taxiId = taxiId;
        this.taxiRadius = taxiRadius;
    }

    public DisLocation getTaxiLocation() {
        return taxiLocation;
    }

    public long getTaxiId() {
        return taxiId;
    }

    public double getTaxiRadius() {
        return taxiRadius;
    }

    public void setTaxiLocation(DisLocation taxiLocation) {
        this.taxiLocation = taxiLocation;
    }

    public void transfer(Passenger passenger) {
        passenger.setArrived(true);
        this.setTaxiLocation(passenger.getDestination());
        LOGGER.info(this.getTaxiId());
        LOGGER.info(this.getTaxiLocation());
        LOGGER.info(passenger.getIsArrived());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Taxi)) {
            return false;
        }
        Taxi taxi = (Taxi) object;
        return taxiId == taxi.taxiId && Double.compare(taxi.taxiRadius, taxiRadius) == 0
                && Objects.equals(taxiLocation, taxi.taxiLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxiLocation, taxiId, taxiRadius);
    }

    @Override
    public String toString() {
        return "Taxi: " + "taxiId = " + taxiId +
                ", taxiLocation = " + taxiLocation +
                ", taxiRadius = " + taxiRadius;
    }
}
