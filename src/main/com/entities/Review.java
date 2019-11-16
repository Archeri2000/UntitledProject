package main.com.entities;

import main.com.utils.ISerialisable;
import main.com.utils.SerialisationUtils;

import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

import static main.com.utils.SerialisationUtils.*;


public class Review implements ISerialisable{
    public String first;
    public Double second;
    public Review(){}
    public Review(String first, Double second){
        if(second == null){
            second = 0.0;
        }
        this.first = first;
        this.second = second;
    }
    public String First(){
        return first;
    }

    public Double Second(){
        return second;
    }

    @Override
    public String toSerialisedString() {
        return SerialisationUtils.serialise(serialiseString(first, "first"), serialiseDouble(second, "second"));
    }

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
