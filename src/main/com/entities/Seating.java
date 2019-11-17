
package main.com.entities;

import main.com.utils.ISerialisable;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;


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

    public String getDisplayString(){
        String output = "";
        for (Seat value : seats){
            while (!value.getID().substring(1, 3).equals("10")){
                output += value.getID() + "\t";
            }
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
        return null;
    }

    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        return null;
    }
}