package test.com.serialisationUtils;

import static main.com.utils.SerialisationUtils.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SerialisationUtilsTest {
    @BeforeEach
    void preTest(){
        fromSerialisedStringCalled = false;
    }
    //Double tests
    @Test
    void testSerialiseDouble(){
        String name = "name";
        double i = 123.45;
        assertEquals(name + getSEPARATOR() + i,
                serialiseDouble(i, name));
    }
    @Test
    void testDeserialiseDouble(){
        String sDbl = "123.45";
        assertEquals(123.45, deserialiseDouble(sDbl));
    }
    @Test
    void testSerialiseDblZero(){
        double d = 0;
        assertEquals("double"+getSEPARATOR()+"0.0", serialiseDouble(d, "double"));
    }
    @Test
    void testDeserialiseDblZero(){
        String d = "0.0";
        assertEquals(0, deserialiseDouble(d));

    }

    //Int tests
    @Test
    void testSerialiseInt(){
        String name = "name";
        int i = 12345;
        assertEquals(name + getSEPARATOR() + i,
                serialiseInt(i, name));
    }
    @Test
    void testSerialiseZero(){
        String name = "name";
        int i = 0;
        assertEquals(name + getSEPARATOR() + i,
                serialiseInt(i, name));
    }
    @Test
    void testDeserialiseInt(){
        String sInt = "12345";
        assertEquals(12345, deserialiseInt(sInt));
    }
    @Test
    void testDeserialiseZero(){
        String sInt = "0";
        assertEquals(0, deserialiseInt(sInt));

    }

    // String tests
    @Test
    void testSerialiseString(){
        String name = "name";
        String s = "The quick brown fox.";
        assertEquals("name"+ getSEPARATOR()+"The quick brown fox.",
                serialiseString(s, name));
    }
    @Test
    void testSerialiseNullString(){
        String name = "name";
        assertEquals("name"+ getSEPARATOR()+getNULLSTR(),
                serialiseString(null, name));
    }
    @Test
    void testDeserialiseString(){
        String s = "The quick brown fox.";
        assertEquals("The quick brown fox.",
                deserialiseString(s));
    }
    @Test
    void testDeserialiseNullString(){
        assertNull(deserialiseString(getNULLSTR()));
    }

    // LocalDateTime tests
    @Test
    void testSerialiseDateTime(){
        String name = "name";
        String datetime = "2015-08-04T10:11:30";
        LocalDateTime time = LocalDateTime.parse("2015-08-04T10:11:30");
        assertEquals(name + getSEPARATOR() + datetime,
                serialiseDateTime(time, name));
    }
    @Test
    void testDeserialiseDateTime(){
        String datetime = "2015-08-04T10:11:30";
        LocalDateTime time = LocalDateTime.parse("2015-08-04T10:11:30");
        assertEquals(time, deserialiseDateTime(datetime));
    }

    //Serialise Deserialise Primitives
    @Test
    void testSerialisePrimitive(){
        String clause1 = "name"+getSEPARATOR()+"val1";
        String clause2 = "name2"+getSEPARATOR()+"val2";
        String clause3 = "name3"+getSEPARATOR()+"val3";
        assertEquals(clause1+getSPLITTER()+clause2+getSPLITTER()+clause3,
                serialise(clause1, clause2, clause3));
    }
    @Test
    void testDeserialisePrimitive() throws InvalidPropertiesFormatException {
        String clause1 = "name"+getSEPARATOR()+"val1";
        String clause2 = "name2"+getSEPARATOR()+"val2";
        String clause3 = "name3"+getSEPARATOR()+"val3";
        HashMap<String, String> hMap = deserialise(serialise(clause1, clause2, clause3));
        assert hMap != null;
        assertEquals("val1", hMap.get("name"));
        assertEquals("val2", hMap.get("name2"));
        assertEquals("val3", hMap.get("name3"));
    }

    //Serialise Deserialise Object
    @Test
    void testSerialiseObjectPrimitivesNull(){
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
    void testDeserialiseObjectPrimitivesNull() throws InvalidPropertiesFormatException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MockSerialisePrimitives testobj = new MockSerialisePrimitives();
        String serialised = serialiseObject(testobj, "name");
        assertTrue(testobj.toSerialisedStringCalled);
        String str = deserialise(serialised).get("name");
        MockSerialisePrimitives deSerial = deserialiseObject(MockSerialisePrimitives.class, str);
        assertTrue(fromSerialisedStringCalled);
        assertEquals(testobj, deSerial);
    }
    @Test
    void testSerialiseObjectPrimitives(){
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
    void testDeserialiseObjectPrimitives() throws InvalidPropertiesFormatException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MockSerialisePrimitives testobj = new MockSerialisePrimitives(10, "TEST STRING", 0.325);
        String serialised = serialiseObject(testobj, "name");
        assertTrue(testobj.toSerialisedStringCalled);
        String str = Objects.requireNonNull(deserialise(serialised)).get("name");
        MockSerialisePrimitives deSerial = deserialiseObject(MockSerialisePrimitives.class, str);
        assertTrue(fromSerialisedStringCalled);
        assertEquals(testobj, deSerial);
    }
    @Test
    void testSerialiseObjectNull(){
        assertEquals("name"+getSEPARATOR()+getStartObject()+getNULLSTR()+getEndObject(),
                serialiseObject(null, "name"));
    }
    @Test
    void testDeserialiseObjectNull() throws InvalidPropertiesFormatException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertNull(deserialiseObject(MockSerialisePrimitives.class, getStartObject()+getNULLSTR()+getEndObject()));
    }
    @Test
    void testSerialiseNested(){
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
    void testDeserialiseNested() throws InvalidPropertiesFormatException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MockSerialisePrimitives prim = new MockSerialisePrimitives(1235, "stringval", 0.34285);
        MockSerialiseNested testobj = new MockSerialiseNested(987, 3.14, prim);
        String serialised = serialiseObject(testobj, "name");
        assertTrue(testobj.toSerialisedStringCalled);
        String str = Objects.requireNonNull(deserialise(serialised)).get("name");
        MockSerialiseNested deSerial = deserialiseObject(MockSerialiseNested.class, str);
        assertTrue(fromSerialisedStringCalled);
        assertEquals(testobj, deSerial);
    }
    @Test
    void testSerialiseDoubleNested(){
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
    void testDeserialiseDoubleNested() throws InvalidPropertiesFormatException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MockSerialisePrimitives prim = new MockSerialisePrimitives(1235, "stringval", 0.34285);
        MockSerialiseNested nst = new MockSerialiseNested(987, 3.14, prim);
        MockSerialiseDoubleNested testobj = new MockSerialiseDoubleNested(369, 1.618, nst);
        String serialised = serialiseObject(testobj, "name");
        assertTrue(testobj.toSerialisedStringCalled);
        String str = Objects.requireNonNull(deserialise(serialised)).get("name");
        MockSerialiseDoubleNested deSerial = deserialiseObject(MockSerialiseDoubleNested.class, str);
        assertTrue(fromSerialisedStringCalled);
        assertEquals(testobj, deSerial);
    }
    @Test
    void testSerialiseMultiNested(){
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
    void testDeserialiseMultiNested() throws InvalidPropertiesFormatException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MockSerialisePrimitives prim = new MockSerialisePrimitives(1235, "stringval", 0.34285);
        MockSerialisePrimitives prim2 = new MockSerialisePrimitives(6789, "stringval", 1.41);
        MockSerialiseMultiNested testobj = new MockSerialiseMultiNested(987, 3.14, prim, prim2);
        String serialised = serialiseObject(testobj, "name");
        assertTrue(testobj.toSerialisedStringCalled);
        String str = Objects.requireNonNull(deserialise(serialised)).get("name");
        MockSerialiseMultiNested deSerial = deserialiseObject(MockSerialiseMultiNested.class, str);
        assertTrue(fromSerialisedStringCalled);
        assertEquals(testobj, deSerial);
    }

    //Lists of ISerialisable Objects
    @Test
    void testSerialiseObjectPrimitivesList(){
        MockSerialisePrimitives prim1 = new MockSerialisePrimitives(1235, "stringval", 0.34285);
        MockSerialisePrimitives prim2 = new MockSerialisePrimitives(4567, "this is a string.", 1.2345);
        List<MockSerialisePrimitives> testobj = new ArrayList<>();
        testobj.add(prim1);
        testobj.add(prim2);
        String serialised = serialiseList(testobj, "name");
        assertTrue(testobj.get(0).toSerialisedStringCalled);
        assertTrue(testobj.get(1).toSerialisedStringCalled);
        assertEquals("name"+getSEPARATOR()+getStartObject()+
                getStartObject()+prim1.toSerialisedString()+
                getEndObject()+getLISTSEPARATOR()+
                getStartObject()+prim2.toSerialisedString()
                +getEndObject()+getEndObject()
                , serialised);
    }
    @Test
    void testDeserialiseObjectPrimitivesList() throws InvalidPropertiesFormatException {
        MockSerialisePrimitives prim1 = new MockSerialisePrimitives(1235, "stringval", 0.34285);
        MockSerialisePrimitives prim2 = new MockSerialisePrimitives(4567, "this is a string.", 1.2345);
        List<MockSerialisePrimitives> testobj = new ArrayList<>();
        testobj.add(prim1);
        testobj.add(prim2);
        String serialised = serialiseList(testobj, "name");
        assertTrue(testobj.get(0).toSerialisedStringCalled);
        assertTrue(testobj.get(1).toSerialisedStringCalled);
        String str = Objects.requireNonNull(deserialise(serialised)).get("name");
        List<MockSerialisePrimitives> deSerial = deserialiseList(MockSerialisePrimitives.class, str);
        assertTrue(fromSerialisedStringCalled);
        assertArrayEquals(testobj.toArray(), deSerial.toArray());
    }
    @Test
    void testSerialiseObjectListNull(){
        List<MockSerialisePrimitives> testobj = new ArrayList<>();
        testobj.add(null);
        assertEquals("name"+getSEPARATOR()+getStartObject()+getStartObject()+getNULLSTR()+getEndObject()+getEndObject(),
                serialiseList(testobj, "name"));
    }
    @Test
    void testDeserialiseObjectListNull() throws InvalidPropertiesFormatException{
        List<MockSerialisePrimitives> testobj = new ArrayList<>();
        testobj.add(null);
        String serial = serialiseList(testobj, "name");
        List<MockSerialisePrimitives> deserial = deserialiseList(MockSerialisePrimitives.class, Objects.requireNonNull(deserialise(serial)).get("name"));
        assertArrayEquals(testobj.toArray(), deserial.toArray());
    }
    @Test
    void testSerialiseNestedList(){
        MockSerialisePrimitives prim1 = new MockSerialisePrimitives(1235, "stringval", 0.34285);
        MockSerialisePrimitives prim2 = new MockSerialisePrimitives(4567, "this is a string.", 1.2345);
        MockSerialiseNested nest1 = new MockSerialiseNested(987, 3.14, prim1);
        MockSerialiseNested nest2 = new MockSerialiseNested(456, 6.28, prim2);
        List<MockSerialiseNested> testobj = new ArrayList<>();
        testobj.add(nest1);
        testobj.add(nest2);
        String serialised = serialiseList(testobj, "name");
        assertTrue(nest1.toSerialisedStringCalled);
        assertTrue(nest2.toSerialisedStringCalled);
        assertEquals("name"+getSEPARATOR()+getStartObject()+
                        getStartObject()+nest1.toSerialisedString()+
                        getEndObject()+getLISTSEPARATOR()+
                        getStartObject()+nest2.toSerialisedString()
                        +getEndObject()+getEndObject()
                , serialised);
    }
    @Test
    void testDeserialiseNestedList() throws InvalidPropertiesFormatException{
        MockSerialisePrimitives prim1 = new MockSerialisePrimitives(1235, "stringval", 0.34285);
        MockSerialisePrimitives prim2 = new MockSerialisePrimitives(4567, "this is a string.", 1.2345);
        MockSerialiseNested nest1 = new MockSerialiseNested(987, 3.14, prim1);
        MockSerialiseNested nest2 = new MockSerialiseNested(456, 6.28, prim2);
        List<MockSerialiseNested> testobj = new ArrayList<>();
        testobj.add(nest1);
        testobj.add(nest2);
        String serialised = serialiseList(testobj, "name");
        assertTrue(nest1.toSerialisedStringCalled);
        assertTrue(nest2.toSerialisedStringCalled);
        String str = Objects.requireNonNull(deserialise(serialised)).get("name");
        List<MockSerialiseNested> deSerial = deserialiseList(MockSerialiseNested.class, str);
        assertTrue(fromSerialisedStringCalled);
        assertArrayEquals(testobj.toArray(), deSerial.toArray());
    }
//    @Test
//    public void testSerialiseDoubleNested(){
//        MockSerialisePrimitives prim = new MockSerialisePrimitives(1235, "stringval", 0.34285);
//        MockSerialiseNested nst = new MockSerialiseNested(987, 3.14, prim);
//        MockSerialiseDoubleNested testobj = new MockSerialiseDoubleNested(369, 1.618, nst);
//        String serialised = serialiseObject(testobj, "name");
//        assertTrue(testobj.toSerialisedStringCalled);
//        assertEquals("name"+getSEPARATOR()+getStartObject()+
//                serialise(
//                        serialiseInt(369, "int"),
//                        serialiseDouble(1.618, "double"),
//                        serialiseObject(nst, "obj")
//                )
//                +getEndObject(), serialised);
//    }
//    @Test
//    public void testDeserialiseDoubleNested() throws InvalidPropertiesFormatException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        MockSerialisePrimitives prim = new MockSerialisePrimitives(1235, "stringval", 0.34285);
//        MockSerialiseNested nst = new MockSerialiseNested(987, 3.14, prim);
//        MockSerialiseDoubleNested testobj = new MockSerialiseDoubleNested(369, 1.618, nst);
//        String serialised = serialiseObject(testobj, "name");
//        assertTrue(testobj.toSerialisedStringCalled);
//        String str = deserialise(serialised).get("name");
//        MockSerialiseDoubleNested deSerial = deserialiseObject(MockSerialiseDoubleNested.class, str);
//        assertTrue(fromSerialisedStringCalled);
//        assertEquals(testobj, deSerial));
//    }
//    @Test
//    public void testSerialiseMultiNested(){
//        MockSerialisePrimitives prim = new MockSerialisePrimitives(1235, "stringval", 0.34285);
//        MockSerialisePrimitives prim2 = new MockSerialisePrimitives(6789, "stringval", 1.41);
//        MockSerialiseMultiNested testobj = new MockSerialiseMultiNested(987, 3.14, prim, prim2);
//        String serialised = serialiseObject(testobj, "name");
//        assertTrue(testobj.toSerialisedStringCalled);
//        assertEquals("name"+getSEPARATOR()+getStartObject()+
//                serialise(
//                        serialiseInt(987, "int"),
//                        serialiseDouble(3.14, "double"),
//                        serialiseObject(prim, "obj"),
//                        serialiseObject(prim2, "obj2")
//                )
//                +getEndObject(), serialised);
//    }
//    @Test
//    public void testDeserialiseMultiNested() throws InvalidPropertiesFormatException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        MockSerialisePrimitives prim = new MockSerialisePrimitives(1235, "stringval", 0.34285);
//        MockSerialisePrimitives prim2 = new MockSerialisePrimitives(6789, "stringval", 1.41);
//        MockSerialiseMultiNested testobj = new MockSerialiseMultiNested(987, 3.14, prim, prim2);
//        String serialised = serialiseObject(testobj, "name");
//        assertTrue(testobj.toSerialisedStringCalled);
//        String str = deserialise(serialised).get("name");
//        MockSerialiseMultiNested deSerial = deserialiseObject(MockSerialiseMultiNested.class, str);
//        assertTrue(fromSerialisedStringCalled);
//        assertEquals(testobj, deSerial));
//    }
//
//

    static boolean fromSerialisedStringCalled = false;
}
