package com.epam.task3multithreading.entity;

import java.util.Objects;

public class Taxi {
    private final long taxiId;

    public Taxi(long taxiId) {
        this.taxiId = taxiId;
    }

    public long getTaxiId() {
        return taxiId;
    }

    public void transfer(Passenger passenger) {
        passenger.changeIsArrived();
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
        return taxiId == taxi.taxiId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxiId);
    }

    @Override
    public String toString() {
        return "Taxi: " + "taxiId = " + taxiId;
    }
}
