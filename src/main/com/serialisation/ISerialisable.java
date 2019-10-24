package main.com.serialisation;

import java.util.InvalidPropertiesFormatException;

public interface ISerialisable {
    String toSerialisedString();
    ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException;
}
