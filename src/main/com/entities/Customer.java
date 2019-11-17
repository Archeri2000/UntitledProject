package main.com.entities;

import main.com.utils.ISerialisable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

import static main.com.utils.SerialisationUtils.*;

/** This class holds the details of a customer
 * @author SS1 Group 6
 * @version 13
 */

public class Customer implements ISerialisable {

	/** name of this customer
     */
    private String name;
    
    /** phone number of this customer
     */
    private int mobileNumber;
    
    /** email of this customer
     */
    private String email;

    /** create a list of bookings
     */
    public List<Booking> bookings = new ArrayList<>();

    public Customer(){}

    /** create a new customer based on customer details
     * @param name			name of this customer
     * @param mobileNumber 	mobile number of this customer
     * @param email			email of this customer
     */
    public Customer(String name, int mobileNumber, String email){
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }
    
    /** create a new customer based on customer details
     * @param name			name of this customer
     * @param mobileNumber 	mobile number of this customer
     * @param email			email of this customer
     * @param booking		list of bookings of this customer
     */
    public Customer(String name, int mobileNumber, String email, List<Booking> booking){
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.bookings = booking;
    }
    
    /** set the name of a customer
     * @param name 		name of a customer
     */
    public void setName(String name) {
        this.name = name;
    }

    /** get the name of a customer
     * @return name of a customer
     */
    public String getName() {
        return name;
    }
    
    /** set the mobile number of a customer
     * @param number		mobile number of a customer
     */
    public void setMobileNumber(int number) {
        this.mobileNumber = number;
    }

    /** get the mobile number of a customer
     * @return mobile number of a customer
     */
    public int getMobileNumber() {
        return mobileNumber;
    }

    /** set the email of a customer
     * @param email 		email of a customer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** get the email of a customer
     * @return email of a customer
     */
    public String getEmail() {
        return email;
    }

    /** get the list of bookings of a customer
     * @return bookings of a customer
     */
    public List <Booking> getBookings(){return bookings; }

    /** add booking for a customer
     * @param booking 			booking of a customer
     * @return True/False if a booking is made successfully
     */
    public boolean addBooking(Booking booking){return bookings.add(booking);}

    
    /**
     * Serialize customer
     * @return serialised string
     */
    @Override
    public String toSerialisedString() {
        return  serialise(
                serialiseString(name, "name"),
                serialiseInt(mobileNumber, "mobile"),
                serialiseString(email, "email"),
                serialiseList(bookings, "bookings")
        );
    }
    
    /**
     * Deserialize movie
     */
    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        HashMap<String, String> pairs = deserialise(s);
        try{
            assert pairs != null;
            String name = deserialiseString(pairs.get("name"));
            int mobile = deserialiseInt(pairs.get("mobile"));
            String email = deserialiseString(pairs.get("email"));
            List<Booking> bookings = deserialiseList(Booking.class, pairs.get("bookings"));
            return new Customer(name, mobile, email, bookings);
        }catch(Exception e){
            throw new InvalidPropertiesFormatException("");
        }    }
}