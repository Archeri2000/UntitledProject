package main.com.entities;

import main.com.utils.ISerialisable;

import java.util.InvalidPropertiesFormatException;
import java.util.List;

public class Booking implements ISerialisable {
    public Booking(){}

    private String TransactionID;
    private Customer customer;
    private List<Seat> seats;

    public Booking(Customer customer, List<Seat> seats, String tid){
        this.customer = customer;
        this.seats = seats;
        this.TransactionID = tid;
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
