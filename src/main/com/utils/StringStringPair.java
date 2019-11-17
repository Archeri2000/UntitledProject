package main.com.utils;

import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

import static main.com.utils.SerialisationUtils.*;


public class StringStringPair implements ISerialisable{
    private String first;
    private String second;
    public StringStringPair(){}
    public StringStringPair(String first, String second){
        this.first = first;
        this.second = second;
    }
    public String First(){
        return first;
    }

    public String Second(){
        return second;
    }

    @Override
    public String toSerialisedString() {
        return SerialisationUtils.serialise(serialiseString(first, "first"), serialiseString(second, "second"));
    }

    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        HashMap<String, String> pairs = deserialise(s);
        assert pairs != null;
        return new StringStringPair(deserialiseString(pairs.get("first")), deserialiseString(pairs.get("second")));
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof StringStringPair)) return false;
        if(this.First() == null){
            return ((StringStringPair)o).First() == null && this.Second().equals(((StringStringPair)o).Second());
        }
        return this.First().equals(((StringStringPair) o).First()) && this.Second().equals(((StringStringPair)o).Second());
    }
}
