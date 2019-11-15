package test.com.utils;

import main.com.utils.StringIntPair;
import static main.com.utils.SerialisationUtils.*;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.InvalidPropertiesFormatException;

import static org.junit.jupiter.api.Assertions.*;

class StringIntPairTest {

    @Test
    void first() {
        StringIntPair testPair = new StringIntPair("HELLO", 1);
        assertEquals("HELLO", testPair.First());
        StringIntPair testPair2 = new StringIntPair(null, 1);
        assertNull(testPair2.First());
    }

    @Test
    void second() {
        StringIntPair testPair = new StringIntPair("HELLO", 6);
        assertEquals(6, testPair.Second());
        StringIntPair testPair2 = new StringIntPair("HELLO", null);
        assertEquals(0, testPair2.Second());

    }

    @Test
    void toSerialisedString() {
        StringIntPair testPair = new StringIntPair("HELLO", 6);
        assertEquals("first"+ getSEPARATOR()+"HELLO"+getSPLITTER()+"second"+getSEPARATOR()+6, testPair.toSerialisedString());
        StringIntPair testPair2 = new StringIntPair(null, 6);
        assertEquals("first"+ getSEPARATOR()+getNULLSTR()+getSPLITTER()+"second"+getSEPARATOR()+6, testPair2.toSerialisedString());
        StringIntPair testPair3 = new StringIntPair("test", null);
        assertEquals("first"+ getSEPARATOR()+"test"+getSPLITTER()+"second"+getSEPARATOR()+0, testPair3.toSerialisedString());
        StringIntPair testPair4 = new StringIntPair(null, null);
        assertEquals("first"+ getSEPARATOR()+getNULLSTR()+getSPLITTER()+"second"+getSEPARATOR()+0, testPair4.toSerialisedString());
    }

    @Test
    void fromSerialisedString() throws InvocationTargetException, NoSuchMethodException, InvalidPropertiesFormatException, InstantiationException, IllegalAccessException {
        StringIntPair testPair = new StringIntPair("HELLO", 6);
        assertEquals(testPair, deserialiseObject(StringIntPair.class, deserialise(serialiseObject(testPair, "test")).get("test")));
        StringIntPair testPair2 = new StringIntPair(null, 6);
        assertEquals(testPair2, deserialiseObject(StringIntPair.class, deserialise(serialiseObject(testPair2, "test")).get("test")));
        StringIntPair testPair3 = new StringIntPair("test", null);
        assertEquals(testPair3, deserialiseObject(StringIntPair.class, deserialise(serialiseObject(testPair3, "test")).get("test")));
        StringIntPair testPair4 = new StringIntPair(null, null);
        assertEquals(testPair4, deserialiseObject(StringIntPair.class, deserialise(serialiseObject(testPair4, "test")).get("test")));
    }
}