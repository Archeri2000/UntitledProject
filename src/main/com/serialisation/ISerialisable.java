package main.com.serialisation;

import java.util.InvalidPropertiesFormatException;

public interface ISerialisable {
    public String toSerialisedString();
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException;
}
