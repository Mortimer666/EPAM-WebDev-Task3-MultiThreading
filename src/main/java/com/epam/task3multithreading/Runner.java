package com.epam.task3multithreading;

import com.epam.task3multithreading.entity.Passenger;
import com.epam.task3multithreading.entity.PassengersStore;
import com.epam.task3multithreading.service.parser.Parser;
import com.epam.task3multithreading.service.parser.ParserException;
import com.epam.task3multithreading.service.parser.ParserJSONImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Runner {
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) throws ParserException, InterruptedException {
        Parser parser = new ParserJSONImpl();
        PassengersStore passengers = parser.parse("src/test/resources/passengers.json");
        for (Passenger passenger : passengers.getPassengers()) {
            LOGGER.info(passenger.getIsArrived());
        }
        ExecutorService executorService = Executors.newFixedThreadPool(passengers.getPassengers().size());
        for (Passenger passenger : passengers.getPassengers()) {
            executorService.submit(passenger);
        }
        executorService.shutdown();
        TimeUnit.MILLISECONDS.sleep(500);
        for (Passenger passenger : passengers.getPassengers()) {
            LOGGER.info(passenger.getIsArrived());
        }
    }
}
