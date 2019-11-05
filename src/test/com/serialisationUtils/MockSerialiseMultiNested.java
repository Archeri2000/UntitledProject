package test.com.serialisationUtils;

import main.com.utils.ISerialisable;

import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

import static main.com.utils.SerialisationUtils.*;

public  class MockSerialiseMultiNested implements ISerialisable {
    boolean toSerialisedStringCalled = false;
    private int int1;
    private double dbl1;
    private MockSerialisePrimitives prim;
    private MockSerialisePrimitives prim2;

    public MockSerialiseMultiNested(){
    }

    public MockSerialiseMultiNested(int int1, double dbl1, MockSerialisePrimitives prim, MockSerialisePrimitives prim2) {
        this.int1 = int1;
        this.dbl1 = dbl1;
        this.prim = prim;
        this.prim2 = prim2;
    }

    @Override
    public String toSerialisedString() {
        toSerialisedStringCalled = true;
        return serialise(
                serialiseInt(int1, "int"),
                serialiseDouble(dbl1, "double"),
                serialiseObject(prim, "obj"),
                serialiseObject(prim2, "obj2"));
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
            MockSerialisePrimitives pm2 = deserialiseObject(MockSerialisePrimitives.class, pairs.get("obj2"));
            return new MockSerialiseMultiNested(i, d, pm, pm2);
        }catch(Exception e){
            throw new InvalidPropertiesFormatException("Invalid Properties");
        }
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof MockSerialiseMultiNested)) return false;
        MockSerialiseMultiNested o = (MockSerialiseMultiNested)obj;
        if(prim != null) return int1 == o.int1 && dbl1 == o.dbl1 && prim.equals(o.prim) ;
        return int1 == o.int1 && dbl1 == o.dbl1 && o.prim == null;
    }
}