package main.com.entities;

import main.com.utils.ISerialisable;
import main.com.utils.StringDoublePair;

import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

import static main.com.utils.SerialisationUtils.*;

/** This class acts as a repository for storing bookings
 * @author SS1 Group 6
 * @version 13
 */
public class Booking implements ISerialisable {
    /**
	 * Default Booking constructor
	 */
    public Booking(){}
    /**
     * TransactionID of the booking
     */
    private String TransactionID;
    /**
     * name of the customer
     */
    private String customerName;
    /**
     * mobile number of the customer
     */
    private int customerMobile;
    /**
     * email address of the customer
     */
    private String customerEmail;
    /**
     * List of tickets
     */
    private List<Ticket> tickets;
    /**
     * name of the movie
     */
    private String movieName;
    /**
     * name of the cineplex
     */
    private String cineplex;
     /**
     * name of the cinema
     */
    private String cinema;

    /**
     * Booking constructor to initialise the booking parameters
     * @param customer - customer object
     * @param tickets - list of tickets booked
     * @param tid - transaction id of the booking
     * @param movieName - name of the movie booked
     * @param cineplex - name of the cineplex booked
     * @param cinema - name of the cinema booked
     */
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
 
    /**
     * Booking constructor to initialise the booking parameters along with the customer details
     * @param customerName - name of the customer
     * @param customerEmail - email of customer
     * @param customerMobile - mobile number of customer
     * @param tickets - list of tickets
     * @param tid - transaction id of the booking
     * @param movieName - name of the movie booked
     * @param cineplex - name of the cineplex booked
     * @param cinema - name of the cinema booked
     */
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

    /**
     * Get transaction id
     * @return String Transactionid
     */
    public String getTransactionID(){
        return TransactionID;
    }
    /**
     * Get customer name
     * @return String customer name
     */


    public String getCustomerName(){
        return customerName;
    }
    /**
     * Get email id of customer
     * @return String email
     */

    public String getCustomerEmail(){
        return customerEmail;
    }

    /**
     * Get mobile number of customer
     * @return String customerMobile
     */
    public int getCustomerMobile(){
        return customerMobile;
    }
    /**
     * Get movie name
     * @return String movieName
     */

    public String getMovieName(){
        return movieName;
    }
    /**
     * Get cineplex name
     * @return String cineplex
     */

    public String getCineplex(){
        return cineplex;
    }
    /**
     * Get cinema name
     * @return String cinema
     */

    public String getCinema(){
        return cinema;
    }
    /**
     * Serialization
     */
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
    
    /**
     * Deserialization
     */

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
