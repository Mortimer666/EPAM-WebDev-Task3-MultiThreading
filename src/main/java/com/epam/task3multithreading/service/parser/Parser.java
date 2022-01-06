package com.epam.task3multithreading.service.parser;

import com.epam.task3multithreading.entity.PassengersStore;

public interface Parser {
    PassengersStore parse(String filePath) throws ParserException;
}
