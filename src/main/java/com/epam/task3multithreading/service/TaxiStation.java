package com.epam.task3multithreading.service;

import com.epam.task3multithreading.entity.Taxi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class TaxiStation {
    private static final Logger LOGGER = LogManager.getLogger(TaxiStation.class);
    private static TaxiStation instance;
    private final List<Taxi> allTaxis;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private final Semaphore semaphore = new Semaphore(3);

    private TaxiStation() {
        TaxiCreator taxiCreator = new TaxiCreator();
        allTaxis = new ArrayList<>();
        allTaxis.add(taxiCreator.createTaxi());
        allTaxis.add(taxiCreator.createTaxi());
        allTaxis.add(taxiCreator.createTaxi());
        LOGGER.info(allTaxis);
    }

    public List<Taxi> getAllTaxis() {
        return allTaxis;
    }

    public static TaxiStation getInstance() {
        TaxiStation localInstance = instance;
        if (localInstance == null) {
            LOCK.lock();
            localInstance = instance;
            if (localInstance == null) {
                instance = localInstance = new TaxiStation();
            }
            LOCK.unlock();
        }
        return localInstance;
    }

    public Taxi orderTaxi() throws TaxiStationException {
        LOGGER.info("Try to ordering taxi");
        try {
            semaphore.acquire();
            LOGGER.info("Still trying");
            LOCK.lock();
            LOGGER.info("Last cab standing");
            Taxi orderedTaxi = allTaxis.get(0);
            allTaxis.remove(0);
            LOGGER.info(orderedTaxi);
            return orderedTaxi;
        } catch (InterruptedException e) {
            throw new TaxiStationException(e.getMessage(), e.fillInStackTrace());
        } finally {
            LOCK.unlock();
        }
    }

    public void releaseTaxi(Taxi taxi) {
        LOGGER.info("Try to release taxi");
        LOCK.lock();
        try {
            allTaxis.add(taxi);
            semaphore.release();
            LOGGER.info("Free taxis: {}", allTaxis);
        } finally {
            LOCK.unlock();
        }
    }
}
