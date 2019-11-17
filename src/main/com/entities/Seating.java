
package main.com.entities;

import main.com.utils.ISerialisable;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;


public class Seating implements ISerialisable {

    List<Seat> seats = new ArrayList<>();
    public Seating(){}
    public List<Seat> getNewSeatingPlan(){
        String[] row = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};
        for (String value : row) {
            for (int i = 1; i < 11; i++) {
                seats.add( new Seat(value + (i), false));
            }
        }
        return seats;
    }
    public boolean isSeaetEmpty(String seat){
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
    public boolean setSeatsOccupied(String[] seatings){
        for (int i = 0; i < seatings.length; i++) {
            for (int j = 0; j < seats.size(); j++) {
                if (seats.get(i).getID().equals(seatings[i])){
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
        return null;
    }

    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        return null;
    }
}