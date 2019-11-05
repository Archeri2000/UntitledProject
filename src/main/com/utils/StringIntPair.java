package main.com.utils;

import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import static main.com.utils.SerialisationUtils.*;


public class StringIntPair implements ISerialisable{
    private String first;
    private Integer second;
    public StringIntPair(){}
    public StringIntPair(String first, Integer second){
        if(second == null){
            second = 0;
        }
        this.first = first;
        this.second = second;
    }
    public String First(){
        return first;
    }

    public Integer Second(){
        return second;
    }

    @Override
    public String toSerialisedString() {
        return SerialisationUtils.serialise(serialiseString(first, "first"), serialiseInt(second, "second"));
    }

    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        HashMap<String, String> pairs = deserialise(s);
        assert pairs != null;
        return new StringIntPair(deserialiseString(pairs.get("first")), deserialiseInt(pairs.get("second")));
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof StringIntPair)) return false;
        if(this.First() == null){
            return ((StringIntPair)o).First() == null && this.Second().equals(((StringIntPair)o).Second());
        }
        return this.First().equals(((StringIntPair) o).First()) && this.Second().equals(((StringIntPair)o).Second());
    }
}
