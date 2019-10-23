package test.com.serialisationUtils;

import static main.com.serialisation.SerialisationUtils.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

import static org.junit.jupiter.api.Assertions.*;

class SerialisationUtilsTest {
    @BeforeEach
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
    public void testSerialisePrimitive(){
        String clause1 = "name"+getSEPARATOR()+"val1";
        String clause2 = "name2"+getSEPARATOR()+"val2";
        String clause3 = "name3"+getSEPARATOR()+"val3";
        assertEquals(clause1+getSPLITTER()+clause2+getSPLITTER()+clause3,
                serialise(clause1, clause2, clause3));
    }
    @Test
    public void testDeserialisePrimitive() throws InvalidPropertiesFormatException {
        String clause1 = "name"+getSEPARATOR()+"val1";
        String clause2 = "name2"+getSEPARATOR()+"val2";
        String clause3 = "name3"+getSEPARATOR()+"val3";
        HashMap<String, String> hMap = deserialise(serialise(clause1, clause2, clause3));
        assertEquals("val1", hMap.get("name"));
        assertEquals("val2", hMap.get("name2"));
        assertEquals("val3", hMap.get("name3"));
    }

    //Serialise Deserialise Object
    @Test
    public void testSerialiseObjectPrimitivesNull(){
        MockSerialisePrimitives testobj = new MockSerialisePrimitives(10, null, 0.34285);
        String serialised = serialiseObject(testobj, "name");
        assertTrue(testobj.toSerialisedStringCalled);
        assertEquals("name"+getSEPARATOR()+getStartObject()+
                serialise(
                        serialiseInt(10, "int"),
                        serialiseString(null, "string"),
                        serialiseDouble(0.34285, "double")
                )
                +getEndObject()
                , serialised);
    }
    @Test
    public void testDeserialiseObjectPrimitivesNull() throws InvalidPropertiesFormatException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MockSerialisePrimitives testobj = new MockSerialisePrimitives();
        String serialised = serialiseObject(testobj, "name");
        assertTrue(testobj.toSerialisedStringCalled);
        String str = deserialise(serialised).get("name");
        MockSerialisePrimitives deSerial = deserialiseObject(MockSerialisePrimitives.class, str);
        assertTrue(fromSerialisedStringCalled);
        assertTrue(testobj.equals(deSerial));
    }
    @Test
    public void testSerialiseObjectPrimitives(){
        MockSerialisePrimitives testobj = new MockSerialisePrimitives(1235, "stringval", 0.34285);
        String serialised = serialiseObject(testobj, "name");
        assertTrue(testobj.toSerialisedStringCalled);
        assertEquals("name"+getSEPARATOR()+getStartObject()+
                serialise(
                        serialiseInt(1235, "int"),
                        serialiseString("stringval", "string"),
                        serialiseDouble(0.34285, "double")
                )
                +getEndObject()
                , serialised);
    }
    @Test
    public void testDeserialiseObjectPrimitives() throws InvalidPropertiesFormatException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MockSerialisePrimitives testobj = new MockSerialisePrimitives(10, "TEST STRING", 0.325);
        String serialised = serialiseObject(testobj, "name");
        assertTrue(testobj.toSerialisedStringCalled);
        String str = deserialise(serialised).get("name");
        MockSerialisePrimitives deSerial = deserialiseObject(MockSerialisePrimitives.class, str);
        assertTrue(fromSerialisedStringCalled);
        assertTrue(testobj.equals(deSerial));
    }
    @Test
    public void testSerialiseObjectNull(){
        assertEquals("name"+getSEPARATOR()+getStartObject()+"`null`"+getEndObject(),
                serialiseObject(null, "name"));
    }
    @Test
    public void testDeserialiseObjectNull() throws InvalidPropertiesFormatException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertNull(deserialiseObject(MockSerialisePrimitives.class, getStartObject()+"`null`"+getEndObject()));
    }
    @Test
    public void testSerialiseNested(){
        MockSerialisePrimitives prim = new MockSerialisePrimitives(1235, "stringval", 0.34285);
        MockSerialiseNested testobj = new MockSerialiseNested(987, 3.14, prim);
        String serialised = serialiseObject(testobj, "name");
        assertTrue(testobj.toSerialisedStringCalled);
        assertEquals("name"+getSEPARATOR()+getStartObject()+
                serialise(
                    serialiseInt(987, "int"),
                    serialiseDouble(3.14, "double"),
                    serialiseObject(prim, "obj")
                )
                +getEndObject(), serialised);
    }
    @Test
    public void testDeserialiseNested() throws InvalidPropertiesFormatException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MockSerialisePrimitives prim = new MockSerialisePrimitives(1235, "stringval", 0.34285);
        MockSerialiseNested testobj = new MockSerialiseNested(987, 3.14, prim);
        String serialised = serialiseObject(testobj, "name");
        assertTrue(testobj.toSerialisedStringCalled);
        String str = deserialise(serialised).get("name");
        MockSerialiseNested deSerial = deserialiseObject(MockSerialiseNested.class, str);
        assertTrue(fromSerialisedStringCalled);
        assertTrue(testobj.equals(deSerial));
    }
    @Test
    public void testSerialiseDoubleNested(){
        MockSerialisePrimitives prim = new MockSerialisePrimitives(1235, "stringval", 0.34285);
        MockSerialiseNested nst = new MockSerialiseNested(987, 3.14, prim);
        MockSerialiseDoubleNested testobj = new MockSerialiseDoubleNested(369, 1.618, nst);
        String serialised = serialiseObject(testobj, "name");
        assertTrue(testobj.toSerialisedStringCalled);
        assertEquals("name"+getSEPARATOR()+getStartObject()+
                serialise(
                        serialiseInt(369, "int"),
                        serialiseDouble(1.618, "double"),
                        serialiseObject(nst, "obj")
                )
                +getEndObject(), serialised);
    }
    @Test
    public void testDeserialiseDoubleNested() throws InvalidPropertiesFormatException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MockSerialisePrimitives prim = new MockSerialisePrimitives(1235, "stringval", 0.34285);
        MockSerialiseNested nst = new MockSerialiseNested(987, 3.14, prim);
        MockSerialiseDoubleNested testobj = new MockSerialiseDoubleNested(369, 1.618, nst);
        String serialised = serialiseObject(testobj, "name");
        assertTrue(testobj.toSerialisedStringCalled);
        String str = deserialise(serialised).get("name");
        MockSerialiseDoubleNested deSerial = deserialiseObject(MockSerialiseDoubleNested.class, str);
        assertTrue(fromSerialisedStringCalled);
        assertTrue(testobj.equals(deSerial));
    }
    @Test
    public void testSerialiseMultiNested(){
        MockSerialisePrimitives prim = new MockSerialisePrimitives(1235, "stringval", 0.34285);
        MockSerialisePrimitives prim2 = new MockSerialisePrimitives(6789, "stringval", 1.41);
        MockSerialiseMultiNested testobj = new MockSerialiseMultiNested(987, 3.14, prim, prim2);
        String serialised = serialiseObject(testobj, "name");
        assertTrue(testobj.toSerialisedStringCalled);
        assertEquals("name"+getSEPARATOR()+getStartObject()+
                serialise(
                        serialiseInt(987, "int"),
                        serialiseDouble(3.14, "double"),
                        serialiseObject(prim, "obj"),
                        serialiseObject(prim2, "obj2")
                )
                +getEndObject(), serialised);
    }
    @Test
    public void testDeserialiseMultiNested() throws InvalidPropertiesFormatException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MockSerialisePrimitives prim = new MockSerialisePrimitives(1235, "stringval", 0.34285);
        MockSerialisePrimitives prim2 = new MockSerialisePrimitives(6789, "stringval", 1.41);
        MockSerialiseMultiNested testobj = new MockSerialiseMultiNested(987, 3.14, prim, prim2);
        String serialised = serialiseObject(testobj, "name");
        assertTrue(testobj.toSerialisedStringCalled);
        String str = deserialise(serialised).get("name");
        MockSerialiseMultiNested deSerial = deserialiseObject(MockSerialiseMultiNested.class, str);
        assertTrue(fromSerialisedStringCalled);
        assertTrue(testobj.equals(deSerial));
    }

    //Lists of ISerialisable Objects


    static boolean fromSerialisedStringCalled = false;
}
