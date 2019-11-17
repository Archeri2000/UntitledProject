package main.com.entities;

import main.com.utils.ISerialisable;

import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

import static main.com.utils.SerialisationUtils.*;

/** This class holds the details of a seat
 * @author SS1 Group 6
 * @version 13
 */
public class Seat implements ISerialisable {
    /** ID of this seat
     */
    private String ID;
    
    /** The status of a seat
     */
    private boolean booked;
    
    public Seat(){}

    /** Creates a new seat with the given ID and status
     * @param ID 		ID of the seat
     * @param status 	status of the seat
     */
    public Seat(String id, boolean status){
        this.ID = id;
        this.booked = status;
    }
    
    /** To set ID of the seat
     * @param ID 		this seat ID
     */
    public void setID(String id) { 
    	this.ID = id;
    }

    /** To get ID of the seat		
     * @return ID of the seat
     */
    public String getID(){
    	return ID;}

    /** To determined if a seat is booked
     */
    public boolean isBooked() {
    	return booked;
    }

    /** To change available seats to booked status
     */
    public void assign()
    {
        if (!booked);
            booked = true;
    }

    /** To change booked seats to available status
     */
    public void unAssign() {
        booked = false;
    }

    /** Serialize seat
     * @return serialised string
     */
    @Override
    public String toSerialisedString() {
        return serialise(
                serialiseString(ID, "id"),
                serialiseInt(booked?1:0, "booked")
        );
    }

    /** Deserialize seat
     */
    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        HashMap<String, String> pairs = deserialise(s);
        try{
            assert pairs != null;
            String ID = deserialiseString(pairs.get("id"));
            boolean booked = deserialiseInt(pairs.get("booked")) == 1;
            return new Seat(ID, booked);
        }catch(Exception e){
            throw new InvalidPropertiesFormatException("");
        }
    }
}
