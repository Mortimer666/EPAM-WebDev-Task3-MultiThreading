package com.epam.task3multithreading.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class DisLocation {
    @JsonProperty
    private double coordinateX;
    @JsonProperty
    private double coordinateY;

    public DisLocation() {
        //  Constructor is empty because objects parsing and creating from JSON
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof DisLocation)) {
            return false;
        }
        DisLocation location = (DisLocation) object;
        return Double.compare(location.coordinateX, coordinateX) == 0 && Double.compare(location.coordinateY, coordinateY) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinateX, coordinateY);
    }

    @Override
    public String toString() {
        return " Location [" +
                "coordinateX = " + coordinateX +
                ", coordinateY = " + coordinateY +
                ']';
    }
}
