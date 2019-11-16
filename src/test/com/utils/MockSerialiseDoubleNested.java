package test.com.utils;

import main.com.utils.ISerialisable;

import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

import static main.com.utils.SerialisationUtils.*;

public  class MockSerialiseDoubleNested implements ISerialisable {
    boolean toSerialisedStringCalled = false;
    private int int1;
    private double dbl1;
    private MockSerialiseNested nst;

    public MockSerialiseDoubleNested(){
    }

    public MockSerialiseDoubleNested(int int1, double dbl1, MockSerialiseNested nst) {
        this.int1 = int1;
        this.dbl1 = dbl1;
        this.nst = nst;
    }

    @Override
    public String toSerialisedString() {
        toSerialisedStringCalled = true;
        return serialise(serialiseInt(int1, "int"),
                serialiseDouble(dbl1, "double"),
                serialiseObject(nst, "obj"));
    }

    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        SerialisationUtilsTest.fromSerialisedStringCalled = true;
        HashMap<String, String> pairs = deserialise(s);
        try{
            assert pairs != null;
            int i = deserialiseInt(pairs.get("int"));
            Double d = deserialiseDouble(pairs.get("double"));
            MockSerialiseNested nest = deserialiseObject(MockSerialiseNested.class, pairs.get("obj"));
            return new MockSerialiseDoubleNested(i, d, nest);
        }catch(Exception e){
            throw new InvalidPropertiesFormatException("Invalid Properties");
        }
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof MockSerialiseDoubleNested)) return false;
        MockSerialiseDoubleNested o = (MockSerialiseDoubleNested) obj;
        if(nst != null) return int1 == o.int1 && dbl1 == o.dbl1 && nst.equals(o.nst) ;
        return int1 == o.int1 && dbl1 == o.dbl1 && o.nst == null;
    }
}