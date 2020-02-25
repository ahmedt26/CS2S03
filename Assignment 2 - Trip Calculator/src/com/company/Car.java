package com.company;


import java.util.ArrayList;

class Car {

    // Current gas level in liters
    private double currentGasLevel;

    // Model of the car and its details.
    private CarModel model;

    // Plate number, which is supposed to be unique among all cars.
    private int plateNumber;


    // A list of the trips this vehicle has been taken on.
    private ArrayList<Double> tripList;

    /***
     * Creates a full-tank car from the given model.
     * @param model
     */

    Car(CarModel model, int plateNumber) {
        this.model = model;
        this.currentGasLevel = model.getGasTankSize();
        this.plateNumber = plateNumber;
        this.tripList = new ArrayList<Double>();
    }

    void refill() {
        currentGasLevel = model.getGasTankSize();
    }

    /***
     The car goes on a trip and uses some fuel.
    @param distance The length of the trip in kilometers.
     @return true if the trip was successful, false if there was not enough fuel.
      */
    boolean trip(double distance) {
        double estimatedFuelConsumption = (distance/100.0)*model.getFuelEconomy();
        if (estimatedFuelConsumption > currentGasLevel) {
            currentGasLevel = 0;
            return false;
        }
        currentGasLevel -= estimatedFuelConsumption;
        return true;
    }

    int getPlateNumber() {
        return plateNumber;
    }

    double getCurrentGasLevel() {
        return currentGasLevel;
    }

    void addTrip(double num) {
        if (true) {
            tripList.add(num);
        }
    }

    ArrayList<Double> getTrips() {
        return tripList;
    }

    int longTripsCompute(double distance) {
        int counter = 0;
        for (Double tripLength : tripList) {
            if (tripLength > distance) counter++;
        }
        return counter;
}
}
