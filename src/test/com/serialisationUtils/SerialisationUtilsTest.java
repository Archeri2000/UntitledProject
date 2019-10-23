package test.com.serialisationUtils;

import main.com.serialisation.ISerialisable;
import static main.com.serialisation.SerialisationUtils.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

import static org.junit.jupiter.api.Assertions.*;

class SerialisationUtilsTest {
    @BeforeAll
    public void preTest(){
        fromSerialisedStringCalled = false;
    }
    //Double tests
    @Test
    public void testSerialiseDouble(){
        String name = "name";
        double i = 123.45;
        assertEquals(name + getSEPARATOR() + i,
                serialiseDouble(i, name));
    }
    @Test
    public void testDeserialiseDouble(){
        String sDbl = "123.45";
        assertEquals(123.45, deserialiseDouble(sDbl));
    }
    @Test
    public void testSerialiseDblZero(){
        double d = 0;
        assertEquals("double"+getSEPARATOR()+"0.0", serialiseDouble(d, "double"));
    }
    @Test
    public void testDeserialiseDblZero(){
        String d = "0.0";
        assertEquals(0, deserialiseDouble(d));

    }

    //Int tests
    @Test
    public void testSerialiseInt(){
        String name = "name";
        int i = 12345;
        assertEquals(name + getSEPARATOR() + i,
                serialiseInt(i, name));
    }
    @Test
    public void testSerialiseZero(){
        String name = "name";
        int i = 0;
        assertEquals(name + getSEPARATOR() + i,
                serialiseInt(i, name));
    }
    @Test
    public void testDeserialiseInt(){
        String sInt = "12345";
        assertEquals(12345, deserialiseInt(sInt));
    }
    @Test
    public void testDeserialiseZero(){
        String sInt = "0";
        assertEquals(0, deserialiseInt(sInt));

    }

    // String tests
    @Test
    public void testSerialiseString(){
        String name = "name";
        String s = "The quick brown fox.";
        assertEquals("name"+ getSEPARATOR()+"The quick brown fox.",
                serialiseString(s, name));
    }
    @Test
    public void testSerialiseNullString(){
        String name = "name";
        assertEquals("name"+ getSEPARATOR()+"`null`",
                serialiseString(null, name));
    }
    @Test
    public void testDeserialiseString(){
        String s = "The quick brown fox.";
        assertEquals("The quick brown fox.",
                deserialiseString(s));
    }
    @Test
    public void testDeserialiseNullString(){
        assertNull(deserialiseString("`null`"));
    }

    // LocalDateTime tests
    @Test
    public void testSerialiseDateTime(){
        String name = "name";
        String datetime = "2015-08-04T10:11:30";
        LocalDateTime time = LocalDateTime.parse("2015-08-04T10:11:30");
        assertEquals(name + getSEPARATOR() + datetime,
                serialiseDateTime(time, name));
    }
    @Test
    public void testDeserialiseDateTime(){
        String datetime = "2015-08-04T10:11:30";
        LocalDateTime time = LocalDateTime.parse("2015-08-04T10:11:30");
        assertEquals(time, deserialiseDateTime(datetime));
    }

    //Serialise Deserialise Primitives
    @Test
    public void testSerialise(){
        String clause1 = "name"+getSEPARATOR()+"val1";
        String clause2 = "name2"+getSEPARATOR()+"val2";
        String clause3 = "name3"+getSEPARATOR()+"val3";
        assertEquals(clause1+getSPLITTER()+clause2+getSPLITTER()+clause3,
                serialise(clause1, clause2, clause3));
    }
    @Test
    public void testDeserialise() throws InvalidPropertiesFormatException {
        String clause1 = "name"+getSEPARATOR()+"val1";
        String clause2 = "name2"+getSEPARATOR()+"val2";
        String clause3 = "name3"+getSEPARATOR()+"val3";
        HashMap<String, String> hMap = deserialise(serialise(clause1, clause2, clause3));
        assertEquals("val1", hMap.get("name"));
        assertEquals("val2", hMap.get("name2"));
        assertEquals("val3", hMap.get("name3"));
    }
    @Test
    public void testSerialiseObjectPrimitivesNull(){
        MockSerialisePrimitives testobj = new MockSerialisePrimitives(10, null, 0.34285);
        String serialised = testobj.toSerialisedString();
        assertTrue(testobj.toSerialisedStringCalled);
        assertEquals(serialise(serialiseInt(10, "int"),
                serialiseString(null, "string"),
                serialiseDouble(0.34285, "double")), serialised);
    }
    @Test
    public void testDeserialiseObjectPrimitivesNull() throws InvalidPropertiesFormatException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MockSerialisePrimitives testobj = new MockSerialisePrimitives();
        String serialised = testobj.toSerialisedString();
        assertTrue(testobj.toSerialisedStringCalled);
        MockSerialisePrimitives deSerial = deserialiseObject(MockSerialisePrimitives.class, serialised);
        assertTrue(fromSerialisedStringCalled);
        assertTrue(testobj.equals(deSerial));
    }
    @Test
    public void testSerialiseObjectPrimitives(){
        MockSerialisePrimitives testobj = new MockSerialisePrimitives(1235, "stringval", 0.34285);
        String serialised = testobj.toSerialisedString();
        assertTrue(testobj.toSerialisedStringCalled);
        assertEquals(serialise(serialiseInt(1235, "int"),
                serialiseString("stringval", "string"),
                serialiseDouble(0.34285, "double")), serialised);
    }
    @Test
    public void testDeserialiseObjectPrimitives() throws InvalidPropertiesFormatException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MockSerialisePrimitives testobj = new MockSerialisePrimitives(10, "TEST STRING", 0.325);
        String serialised = testobj.toSerialisedString();
        assertTrue(testobj.toSerialisedStringCalled);
        MockSerialisePrimitives deSerial = deserialiseObject(MockSerialisePrimitives.class, serialised);
        assertTrue(fromSerialisedStringCalled);
        assertTrue(testobj.equals(deSerial));
    }
    @Test
    public void testSerialiseObjectNull(){
        assertEquals("name:`null`", serialiseObject(null, "name"));
    }
    @Test
    public void testDeserialiseObjectNull() throws InvalidPropertiesFormatException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertNull(deserialiseObject(MockSerialisePrimitives.class, "`null`"));
    }

    @Test
    public void testSerialiseNested(){}

    @Test
    public void testSerialiseMultiNested(){}

    static boolean fromSerialisedStringCalled = false;
}
