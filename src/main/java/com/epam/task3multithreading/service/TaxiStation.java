package com.epam.task3multithreading.service;

import com.epam.task3multithreading.entity.Taxi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaxiStation {
    private static final Logger LOGGER = LogManager.getLogger(TaxiStation.class);
    private static TaxiStation instance;
    private final Queue<Taxi> allTaxis = new ArrayDeque<>();
    private static final Lock LOCK = new ReentrantLock();
    private final Semaphore semaphore = new Semaphore(3);

    private TaxiStation() {
        initTaxiStation();
    }

    private Taxi initTaxi() {
        return new Taxi((long) (Math.random() * 1_000_000));
    }

    private void initTaxiStation() {
        allTaxis.add(initTaxi());
        allTaxis.add(initTaxi());
        allTaxis.add(initTaxi());
    }

    public static TaxiStation getInstance() {
        TaxiStation localInstance = instance;
        if (localInstance == null) {
            LOCK.lock();
            try {
            localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new TaxiStation();
                }
            } finally {
                LOCK.unlock();
            }
        }
        return localInstance;
    }

    public Taxi orderTaxi() throws TaxiStationException {
        LOGGER.info("Ordering taxi");
        try {
            semaphore.acquire();
            LOCK.lock();
            return allTaxis.poll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new TaxiStationException(e.getMessage(), e.fillInStackTrace());
        } finally {
            LOCK.unlock();
        }
    }

    public void releaseTaxi(Taxi taxi) {
        LOCK.lock();
        try {
            allTaxis.add(taxi);
        } finally {
            semaphore.release();
            LOCK.unlock();
        }
    }
}
