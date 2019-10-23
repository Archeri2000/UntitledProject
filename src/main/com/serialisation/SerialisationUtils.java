package main.com.serialisation;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Set of serialisation utilities to use with ISerialisable
 */
public class SerialisationUtils {
    // Static characters
    private static final char SPLITTER = '~';
    private static final char START_OBJECT = '{';
    private static final char END_OBJECT = '}';
    private static final char SEPARATOR = '#';
    private static final String NULLSTR = "`null`";

    // Static getters for tests
    public static char getSPLITTER(){
        return SPLITTER;
    }

    public static char getStartObject() {
        return START_OBJECT;
    }

    public static char getEndObject() {
        return END_OBJECT;
    }

    public static char getSEPARATOR() {
        return SEPARATOR;
    }

    public static String getNULLSTR(){
        return NULLSTR;
    }

    /**
     * Serialises a list of serialised strings to a single string
     * @param strs - Serialised strings to concatenate
     * @return strings concatenated with SPLITTER
     */
    public static String serialise(String ... strs){
        return String.join(Character.toString(SPLITTER), strs);
    }

    /**
     * Splits a serialised object string to a hashmap of serialised strings
     * @param s - Input string
     * @return Hashmap of name, serialised string pairs
     * @throws InvalidPropertiesFormatException
     */
    public static HashMap<String, String> deserialise(String s) throws InvalidPropertiesFormatException {
        if(s.equals(NULLSTR)) return null;
        HashMap<String, String> pairs = new HashMap<>();
        int sep = -1;
        int nesting = 0;
        char curr;
        boolean flag = false;
        while(s.length() > 0) {
            for (int i = 0; i < s.length(); i++) {
                curr = s.charAt(i);
                if (curr == SEPARATOR && sep == -1 && nesting == 0){
                    sep = i;
                }else if (curr == START_OBJECT) {
                    nesting += 1;
                } else if (curr == END_OBJECT) {
                    nesting -= 1;
                    if (nesting < 0) {
                        throw new InvalidPropertiesFormatException("Invalid String1!");
                    }
                } else if (curr == SPLITTER && nesting == 0) {
                    if(sep == -1){
                        throw new InvalidPropertiesFormatException("Invalid String2!");
                    }
                    flag = true;
                    pairs.put(s.substring(0, sep), s.substring(sep+1, i));
                    if(i != s.length()-1) s = s.substring(i+1);
                    sep = -1;
                    break;
                }
            }
            if(!flag){
                if(sep != -1 && nesting == 0) pairs.put(s.substring(0, sep), s.substring(sep+1));
                else throw new InvalidPropertiesFormatException("Invalid String3!");
                break;
            }
            flag = false;
        }
        return pairs;
    }

    /**
     * Serialises an ISerialisable object
     * @param obj - Object to be serialised
     * @param name - Name of property
     * @param <T> - Type of the object
     * @return object serialised to string
     */
    public static <T extends ISerialisable> String serialiseObject(T obj, String name){
        if(obj == null) return CreateKVPair(name, START_OBJECT+NULLSTR+END_OBJECT);
        return CreateKVPair(name, START_OBJECT+obj.toSerialisedString()+END_OBJECT);
    }

    /**
     * Deserialises object from serialised string
     * @param tClass - Class of the object to retrieve
     * @param s - Serialised String
     * @param <T> - Type of the object to retrieve
     * @return Retrieved object of type T
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws InvalidPropertiesFormatException
     */
    public static <T extends ISerialisable> T deserialiseObject(Class<T> tClass, String s) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InvalidPropertiesFormatException {
        s = s.substring(1,s.length()-1);
        if(s.equals(NULLSTR)) return null;
        else return (T)tClass.getConstructor().newInstance().fromSerialisedString(s);
    }

    /**
     * Serialises a list of objects implementing ISerialisable
     * @param objects - List of serialisable objects
     * @param name - Name of property
     * @param <T> - Type of Serialisable Object
     * @return String representing serialised object list
     */
    public static <T extends ISerialisable> String serialiseList(List<T> objects, String name){
        return CreateKVPair(name, START_OBJECT + objects.stream()
                                        .map(x -> {
                                            if(x == null)return NULLSTR;
                                            else return x.toSerialisedString();})
                                        .collect(Collectors.joining(",")) + END_OBJECT);
    }

    public static <T extends ISerialisable> List<T> deserialiseList(Class<T> tClass, String s){
        s = s.substring(1, s.length()-1);
        List<String> strList = Arrays.asList(s.split(","));
        return strList.stream().map(x -> {
            if(x.equals(NULLSTR)) return null;
            try {
                return SerialisationUtils.deserialiseObject(tClass, x);
            } catch (InvalidPropertiesFormatException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());
    }

    public static String serialiseDouble(double f, String name){
        return CreateKVPair(name, Double.toString(f));
    }

    public static Double deserialiseDouble(String value){
        return Double.parseDouble(value);
    }

    public static String serialiseInt(int i, String name){
        return CreateKVPair(name, Integer.toString(i));
    }

    public static Integer deserialiseInt(String value){
        return Integer.parseInt(value);
    }

    public static String serialiseString(String s, String name){
        if(s != null)return CreateKVPair(name, s);
        else return CreateKVPair(name, NULLSTR);
    }
    public static String deserialiseString(String s){
        if(s.equals(NULLSTR))return null;
        else return s;
    }

    public static String serialiseDateTime(LocalDateTime dt, String name){
        return CreateKVPair(name, dt.toString());
    }

    public static LocalDateTime deserialiseDateTime(String dt){
        return LocalDateTime.parse(dt);
    }


    private static String CreateKVPair(String key, String value){
        return key + SEPARATOR + value;
    }


}
