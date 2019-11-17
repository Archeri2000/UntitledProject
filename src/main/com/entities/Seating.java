
package main.com.entities;

import main.com.utils.ISerialisable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

import static main.com.utils.SerialisationUtils.*;


public class Seating implements ISerialisable {

    private List<Seat> seats = new ArrayList<>();

    public Seating(){
        String[] row = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};
        for (String value : row) {
            for (int i = 1; i < 11; i++) {
                seats.add( new Seat(value + (i), false));
            }
        }
    }

    public Seating(List<Seat> seats){
        this.seats = seats;
    }

    public String getDisplayString(){
        String output = "";

        int k = 0;
        for (int i =0; i< 8; i++){
            for (int j =0; j< 10; j++){
                output += seats.get(k+j).getID() + "\t";
            }
            k+=10;
            output += "\n";
        }

        return output;
    }
    public boolean isSeatEmpty(String seat){
        for (int i = 0; i < seats.size(); i++){
            if (seats.get(i).getID().equals(seat)){
                if(seats.get(i).isBooked()){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public boolean setSeatsOccupied(ArrayList<String> seatings){
        for (int i = 0; i < seatings.size(); i++) {
            for (int j = 0; j < seats.size(); j++) {
                if (seats.get(i).getID().equals(seatings.get(i))){
                    if(seats.get(i).isBooked()) {
                        return false;
                    }else {
                        seats.get(i).assign();
                        return true;
                    }
                }
            }
        }return false;
    }

    @Override
    public String toSerialisedString() {
        return serialise(
                serialiseList(seats, "seats")
        );
    }

    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        HashMap<String, String> pairs = deserialise(s);
        assert pairs != null;
        List<Seat> seats = deserialiseList(Seat.class, pairs.get("seats"));
        return new Seating(seats);
    }
}