package main.com.utils;

import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

import static main.com.utils.SerialisationUtils.*;


public class StringDoublePair implements ISerialisable{
    private String first;
    private Double second;
    public StringDoublePair(){}
    public StringDoublePair(String first, Double second){
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
        return new StringDoublePair(deserialiseString(pairs.get("first")), deserialiseDouble(pairs.get("second")));
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof StringDoublePair)) return false;
        if(this.First() == null){
            return ((StringDoublePair)o).First() == null && this.Second().equals(((StringDoublePair)o).Second());
        }
        return this.First().equals(((StringDoublePair) o).First()) && this.Second().equals(((StringDoublePair)o).Second());
    }
}
