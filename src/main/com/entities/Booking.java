package main.com.entities;

import main.com.utils.ISerialisable;
import main.com.utils.StringDoublePair;

import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

import static main.com.utils.SerialisationUtils.*;

public class Booking implements ISerialisable {
    public Booking(){}

    private String TransactionID;
    private String customerName;
    private int customerMobile;
    private String customerEmail;
    private List<Ticket> tickets;
    private String movieName;
    private String cineplex;
    private String cinema;

    public Booking(Customer customer, List<Ticket> tickets, String tid, String movieName, String cineplex, String cinema){
        this.customerName = customer.getName();
        this.customerEmail = customer.getEmail();
        this.customerMobile = customer.getMobileNumber();
        this.tickets = tickets;
        this.TransactionID = tid;
        this.cinema = cinema;
        this.cineplex = cineplex;
        this.movieName = movieName;
    }

    public Booking(String customerName, String customerEmail, int customerMobile, List<Ticket> tickets, String tid, String movieName, String cineplex, String cinema){
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerMobile = customerMobile;
        this.tickets = tickets;
        this.TransactionID = tid;
        this.cinema = cinema;
        this.cineplex = cineplex;
        this.movieName = movieName;
    }

    public String getTransactionID(){
        return TransactionID;
    }

    public String getCustomerName(){
        return customerName;
    }

    public String getCustomerEmail(){
        return customerEmail;
    }

    public int getCustomerMobile(){
        return customerMobile;
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
        return serialise(
                serialiseString(TransactionID, "tID"),
                serialiseString(customerName, "name"),
                serialiseString(customerEmail, "email"),
                serialiseInt(customerMobile, "mobile"),
                serialiseString(movieName, "movie"),
                serialiseString(cineplex, "cineplex"),
                serialiseString(cinema, "cinema"),
                serialiseList(tickets, "tickets")
        );
    }

    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        HashMap<String, String> pairs = deserialise(s);
        try{
            assert pairs != null;
            String tID = deserialiseString(pairs.get("tID"));
            String name = deserialiseString(pairs.get("name"));
            String email = deserialiseString(pairs.get("email"));
            int mobile = deserialiseInt(pairs.get("mobile"));
            String movie = deserialiseString(pairs.get("movie"));
            String cineplex = deserialiseString(pairs.get("cineplex"));
            String cinema = deserialiseString(pairs.get("cinema"));
            List<Ticket> tickets = deserialiseList(Ticket.class, pairs.get("tickets"));
            return new Booking(name, email, mobile, tickets, tID, movie, cineplex, cinema);
        }catch(Exception e){
            throw new InvalidPropertiesFormatException("");
        }
    }
}
