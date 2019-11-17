package main.com.entities;

import main.com.utils.ISerialisable;

import java.util.InvalidPropertiesFormatException;
import java.util.List;

public class Booking implements ISerialisable {
    public Booking(){}

    private String TransactionID;
    private Customer customer;
    private List<Ticket> seats;
    private String movieName;
    private String cineplex;
    private String cinema;

    public Booking(Customer customer, List<Ticket> seats, String tid){
        this.customer = customer;
        this.seats = seats;
        this.TransactionID = tid;
    }

    public String getTransactionID(){
        return TransactionID;
    }

    public Customer getCustomer(){
        return customer;
    }

    public String getMovieName(){
        return movieName;
    }

    public String getCineplex(){
        return cineplex;
    }

    public String getCinema(){
        return cinema;
    }
    @Override
    public String toSerialisedString() {
        return "";
    }

    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        return new Booking();
    }
}
