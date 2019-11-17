package main.com.entities;

import main.com.utils.ISerialisable;
import main.com.utils.SerialisationUtils;

import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

import static main.com.utils.SerialisationUtils.*;

/** This class holds the details of a ticket
 * @author SS1 Group 6
 * @version 13
 */
public class Ticket implements ISerialisable {
	 /** seat of the cinema
     */
    private Seat seat;
    
    /** agegroup that the customer belong to
     */
    private AgeGroup ageGroup;
    
    /** price of ticket
     */
    private double price;
    
    /** Create constructor
     * 
     */
    public Ticket(){}
    
    /** Creates a new ticket with the seat, age and price
     * @param seat		seat that customer booked
     * @param age		age of customer
     * @param price		price of ticket
     */
    public Ticket(Seat seat, AgeGroup age, double price){
        this.seat = seat;
        this.ageGroup = age;
        this.price = price;
    }

    /**
     * Serialize ticket
     * @return serialised string
     */
    @Override
    public String toSerialisedString() {
        return serialise(
            serialiseString(ageGroup.name(), "age"),
            serialiseObject(seat, "seat"),
            serialiseDouble(price, "price")
        );
    }

    /** Deserialize ticket
     * 
     */
    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        HashMap<String, String> pairs = SerialisationUtils.deserialise(s);
        try{
            assert pairs != null;
            Seat seat = deserialiseObject(Seat.class, pairs.get("seat"));
            AgeGroup age = AgeGroup.valueOf(deserialiseString(pairs.get("age")));
            double price = deserialiseDouble(pairs.get("price"));
            return new Ticket(seat, age, price);
        }catch(Exception e){
            throw new InvalidPropertiesFormatException("");
        }
    }
}