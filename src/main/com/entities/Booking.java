package main.com.entities;

import main.com.utils.ISerialisable;

import java.util.InvalidPropertiesFormatException;
import java.util.List;

public class Booking implements ISerialisable {
    public Booking(){}

    private String TransactionID;
    private List<String> seats;

    @Override
    public String toSerialisedString() {
        return null;
    }

    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        return null;
    }
}