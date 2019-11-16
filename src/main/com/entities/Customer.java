package main.com.entities;

import main.com.utils.ISerialisable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

import static main.com.utils.SerialisationUtils.*;

public class Customer implements ISerialisable {

    private String name;
    private int mobileNumber;
    private String email;

    public List<Booking> bookings = new ArrayList<>();

    public Customer(){}

    public Customer(String name, int mobileNumber, String email){
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    public Customer(String name, int mobileNumber, String email, List<Booking> booking){
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.bookings = booking;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMobileNumber(int number) {
        this.mobileNumber = number;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public List <Booking> getBookings(){return bookings; }

    public boolean addBooking(Booking booking){return bookings.add(booking);}

    @Override
    public String toSerialisedString() {
        return  serialise(
                serialiseString(name, "name"),
                serialiseInt(mobileNumber, "mobile"),
                serialiseString(email, "email"),
                serialiseList(bookings, "bookings")
        );
    }

    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        HashMap<String, String> pairs = deserialise(s);
        try{
            assert pairs != null;
            String name = deserialiseString(pairs.get("name"));
            int mobile = deserialiseInt(pairs.get("mobile"));
            String email = deserialiseString(pairs.get("email"));
            List<Booking> bookings = deserialiseList(Booking.class, pairs.get("bookings"));
            return new Customer(name, mobileNumber, email, bookings);
        }catch(Exception e){
            throw new InvalidPropertiesFormatException("");
        }    }
}
