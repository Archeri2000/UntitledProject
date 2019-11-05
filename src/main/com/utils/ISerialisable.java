package main.com.utils;

import java.util.InvalidPropertiesFormatException;

public interface ISerialisable {
    String toSerialisedString();
    ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException;
}
