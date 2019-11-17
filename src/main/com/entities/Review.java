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
public class Review implements ISerialisable{
	
	 /** review of the movie
     */
    public String first;
    
	 /** rating of the movie
     */
    public Double second;
    public Review(){}
    
    /** Constructor
     * @param first - review string
     * @param second - rating value
     */
    public Review(String first, Double second){
        if(second == null){
            second = 0.0;
        }
        this.first = first;
        this.second = second;
    }
    /** Get users review
     * @return review
     */
    public String First(){
        return first;
    }

    /** Get users rating
     * @return rating
     */
    public Double Second(){
        return second;
    }

	 /**
     * Serialize reviews
     * @return serialised string
     */
    @Override
    public String toSerialisedString() {
        return SerialisationUtils.serialise(serialiseString(first, "first"), serialiseDouble(second, "second"));
    }

    /** Deserialize reviews
     */
    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        HashMap<String, String> pairs = deserialise(s);
        assert pairs != null;
        return new Review(deserialiseString(pairs.get("first")), deserialiseDouble(pairs.get("second")));
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Review)) return false;
        if(this.First() == null){
            return ((Review)o).First() == null && this.Second().equals(((Review)o).Second());
        }
        return this.First().equals(((Review) o).First()) && this.Second().equals(((Review)o).Second());
    }
}
