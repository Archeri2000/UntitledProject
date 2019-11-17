package main.com.entities;

import main.com.utils.ISerialisable;
import main.com.utils.SerialisationUtils;

import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

import static main.com.utils.SerialisationUtils.*;

public class Ticket implements ISerialisable {
    private Seat seat;
    private AgeGroup ageGroup;
    public Ticket(){}
    public Ticket(Seat seat, AgeGroup age){
        this.seat = seat;
        this.ageGroup = age;
    }

    @Override
    public String toSerialisedString() {
        return serialise(
            serialiseString(ageGroup.name(), "age"),
            serialiseObject(seat, "seat")
        );
    }

    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        HashMap<String, String> pairs = SerialisationUtils.deserialise(s);
        try{
            assert pairs != null;
            Seat seat = deserialiseObject(Seat.class, pairs.get("seat"));
            AgeGroup age = AgeGroup.valueOf(deserialiseString(pairs.get("age")));
            return new Ticket(seat, age);
        }catch(Exception e){
            throw new InvalidPropertiesFormatException("");
        }
    }
}
