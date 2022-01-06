package com.epam.task3multithreading.service;

import com.epam.task3multithreading.entity.DisLocation;
import com.epam.task3multithreading.entity.Taxi;

public class TaxiCreator {
    public Taxi createTaxi() {
        DisLocation disLocation = new DisLocation();
        double xCoordinate = Math.random() * 1000;
        disLocation.setCoordinateX(xCoordinate);
        double yCoordinate = Math.random() * 1000;
        disLocation.setCoordinateY(yCoordinate);
        long id = (long) (Math.random() * 100);
        double radius = Math.random() * 2000;
        return new Taxi(disLocation, id, radius);
    }
}
