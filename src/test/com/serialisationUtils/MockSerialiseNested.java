package test.com.serialisationUtils;

import main.com.serialisation.ISerialisable;

import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

import static main.com.serialisation.SerialisationUtils.*;

public  class MockSerialiseNested implements ISerialisable {
    boolean toSerialisedStringCalled = false;
    private int int1;
    private double dbl1;
    private MockSerialisePrimitives prim;

    public MockSerialiseNested(){
    }

    public MockSerialiseNested(int int1, double dbl1, MockSerialisePrimitives prim) {
        this.int1 = int1;
        this.dbl1 = dbl1;
        this.prim = prim;
    }

    @Override
    public String toSerialisedString() {
        toSerialisedStringCalled = true;
        return serialise(serialiseInt(int1, "int"),
                serialiseDouble(dbl1, "double"),
                serialiseObject(prim, "obj"));
    }

    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        SerialisationUtilsTest.fromSerialisedStringCalled = true;
        HashMap<String, String> pairs = deserialise(s);
        try{
            assert pairs != null;
            int i = deserialiseInt(pairs.get("int"));
            Double d = deserialiseDouble(pairs.get("double"));
            MockSerialisePrimitives pm = deserialiseObject(MockSerialisePrimitives.class, pairs.get("obj"));
            return new MockSerialiseNested(i, d, pm);
        }catch(Exception e){
            throw new InvalidPropertiesFormatException("Invalid Properties");
        }
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof MockSerialiseNested)) return false;
        MockSerialiseNested o = (MockSerialiseNested)obj;
        if(prim != null) return int1 == o.int1 && dbl1 == o.dbl1 && prim.equals(o.prim) ;
        return int1 == o.int1 && dbl1 == o.dbl1 && o.prim == null;
    }
}