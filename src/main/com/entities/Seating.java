package main.com.entities;

import main.com.utils.ISerialisable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

import static main.com.utils.SerialisationUtils.*;

/** This class holds the seating plan of a cinema
 * @author SS1 Group 6
 * @version 13
 */

public class Seating implements ISerialisable {


    /**
     * Create array for the seating
     */
    private List<Seat> seats = new ArrayList<>();


    /**
     * Create seating constructor
     */
    public Seating(){
        String[] row = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};
        for (String value : row) {
            for (int i = 1; i < 11; i++) {
                seats.add( new Seat(value + (i), false));
            }
        }
    }

    /**
     * Create seating
     *@param seats- used to store the list of seats
     */
    public Seating(List<Seat> seats){
        this.seats = seats;
    }

    /**
     * To display seating plan
     * @return an array which is the seating plan
     */
    public String getDisplayString(){
        String output = "";

        int k = 0;
        for (int i =0; i< 8; i++){
            for (int j =0; j< 10; j++){
                if (seats.get(k+j).isBooked()) {
                    output += "xx\t";
                }
                else if (j == 5) {
                    output += "\t\t";
                    output += seats.get(k+j).getID() + "\t";
                }else
                    output += seats.get(k+j).getID() + "\t";
            }
            k+=10;
            output += "\n";
        }

        return output;
    }

    /**
     * To determine if seat is empty
     * @param seat 		the seat number
     * @return True/False if these seats are empty or occupied
     */
    public boolean isSeatEmpty(String seat){
        for (int i = 0; i < seats.size(); i++){
            if (seats.get(i).getID().equals(seat)){
                if(!seats.get(i).isBooked()){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * To change seats from available to occupied
     * @param seatings - list of seatings
     * @return True/False if these seats have been successfully set as occupied
     */
    public boolean setSeatsOccupied(ArrayList<String> seatings){
        for (int i = 0; i < seatings.size(); i++) {
            for (int j = 0; j < seats.size(); j++) {
                if (seats.get(j).getID().equals(seatings.get(i))){
                    if(seats.get(j).isBooked()) {
                        return false;
                    }else {
                        seats.get(j).assign();

                        return true;
                    }
                }
            }
        }return false;
    }

    /**
     * Serialize seats
     * @return serialised string
     */
    @Override
    public String toSerialisedString() {
        return serialise(
                serialiseList(seats, "seats")
        );
    }

    /**
     * Deserialize seats
     */
    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        HashMap<String, String> pairs = deserialise(s);
        assert pairs != null;
        List<Seat> seats = deserialiseList(Seat.class, pairs.get("seats"));
        return new Seating(seats);
    }
}
