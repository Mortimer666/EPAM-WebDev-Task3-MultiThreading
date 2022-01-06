package com.epam.task3multithreading.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PassengersStore {
    @JsonProperty
    private ArrayList<Passenger> passengers;

    public PassengersStore() {
        //  Constructor is empty because objects parsing and creating from JSON
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    @Override
    public String toString() {
        return "PassengersStore: " +
                passengers;
    }
}
