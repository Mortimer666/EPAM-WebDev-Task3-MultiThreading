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

public class Runner {
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);
    private static final String PATH = "src/test/resources/passengers.json";

    public static void main(String[] args) {
        try {
            process();
        } catch (ParserException exception) {
            LOGGER.error(exception.getMessage(), exception.fillInStackTrace());
        }

    }

    private static void process() throws ParserException {
        Parser parser = new ParserJSONImpl();
        PassengersStore passengers = parser.parse(PATH);
        ExecutorService executorService = Executors.newFixedThreadPool(passengers.getPassengers().size());
        for (Passenger passenger : passengers.getPassengers()) {
            executorService.execute(passenger);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
//            Waiting while our threads completely stops.
        }
    }
}
