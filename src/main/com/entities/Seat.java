package main.com.entities;

import main.com.utils.ISerialisable;

import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

import static main.com.utils.SerialisationUtils.*;

public class Seat implements ISerialisable {
    private String ID;
    private boolean booked;
    public Seat(){}
    public Seat(String id, boolean status){
        this.ID = id;
        this.booked = status;
    }
    public void setID(String id) { this.ID = id;}

    public String getID(){return ID;}

    public boolean isBooked(){return booked;}

    public void assign()
    {
        if (!booked);
            booked = true;
    }

    public void unAssign() {
        booked = false;
    }

    @Override
    public String toSerialisedString() {
        return serialise(
                serialiseString(ID, "id"),
                serialiseInt(booked?1:0, "booked")
        );
    }

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
