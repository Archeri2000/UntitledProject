package main.com.entities;

import main.com.utils.ISerialisable;

import java.util.InvalidPropertiesFormatException;

public class Ticket implements ISerialisable {
    private Seat seat;
    private AgeGroup ageGroup;
    public Ticket(){}
    public Ticket(Seat seat, AgeGroup age){
        this.seat = seat;
        this.ageGroup = age;
    }

    @Override
    public String toSerialisedString() {
        return null;
    }

    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        return null;
    }
}
