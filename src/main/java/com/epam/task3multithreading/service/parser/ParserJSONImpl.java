package com.epam.task3multithreading.service.parser;

import com.epam.task3multithreading.entity.PassengersStore;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ParserJSONImpl implements Parser {

    public PassengersStore parse(String filePath) throws ParserException {
        ObjectMapper mapper = new ObjectMapper();
        PassengersStore passengers;
        try (JsonParser jsonParser = mapper.createParser(new File(filePath))) {
            passengers = jsonParser.readValueAs(PassengersStore.class);
        } catch (IOException e) {
            throw new ParserException(e.getMessage(), e.fillInStackTrace());
        }
        return passengers;
    }

}
