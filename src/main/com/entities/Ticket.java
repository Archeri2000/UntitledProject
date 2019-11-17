package main.com.entities;

import main.com.utils.ISerialisable;
import main.com.utils.SerialisationUtils;

import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

import static main.com.utils.SerialisationUtils.*;

public class Ticket implements ISerialisable {
    private Seat seat;
    private AgeGroup ageGroup;
    private double price;
    public Ticket(){}
    public Ticket(Seat seat, AgeGroup age, double price){
        this.seat = seat;
        this.ageGroup = age;
        this.price = price;
    }

    @Override
    public String toSerialisedString() {
        return serialise(
            serialiseString(ageGroup.name(), "age"),
            serialiseObject(seat, "seat"),
            serialiseDouble(price, "price")
        );
    }

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
