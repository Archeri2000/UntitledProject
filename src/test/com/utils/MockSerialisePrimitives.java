package test.com.utils;

import main.com.utils.ISerialisable;

import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

import static main.com.utils.SerialisationUtils.*;

public  class MockSerialisePrimitives implements ISerialisable {
    boolean toSerialisedStringCalled = false;
    private int int1;
    private String str;
    private double dbl1;
    public MockSerialisePrimitives(){
    }

    public MockSerialisePrimitives(int int1, String str, double dbl1) {
        this.int1 = int1;
        this.str = str;
        this.dbl1 = dbl1;
    }

    @Override
    public String toSerialisedString() {
        toSerialisedStringCalled = true;
        return serialise(serialiseInt(int1, "int"),
                serialiseString(str, "string"),
                serialiseDouble(dbl1, "double"));
    }

    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        SerialisationUtilsTest.fromSerialisedStringCalled = true;
        HashMap<String, String> pairs = deserialise(s);
        try{
            assert pairs != null;
            int i = deserialiseInt(pairs.get("int"));
            String str = deserialiseString(pairs.get("string"));
            Double d = deserialiseDouble(pairs.get("double"));
            return new MockSerialisePrimitives(i, str, d);
        }catch(Exception e){
            throw new InvalidPropertiesFormatException("Invalid Properties");
        }
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof MockSerialisePrimitives)) return false;
        MockSerialisePrimitives o = (MockSerialisePrimitives) obj;
        if(str != null) return int1 == o.int1 && dbl1 == o.dbl1 && str.equals(o.str);
        return int1 == o.int1 && dbl1 == o.dbl1 && o.str == null;
    }
}