package main.com.repositories;

import main.com.entities.Cineplex;
import main.com.entities.Movie;
import main.com.utils.ISerialisable;
import main.com.utils.StringIntPair;

import java.util.*;

import static main.com.utils.SerialisationUtils.*;
import static main.com.utils.SerialisationUtils.deserialiseList;

/** This class acts as a repository for storing Cineplexes
 * @author SS1 Group 6
 * @version 13
 */
public class CineplexRepository implements ISerialisable {
    
    /**Creation of a Cineplex hashmap
	 * 
	 */
    private HashMap<String, Cineplex> cineplexHashMap = new HashMap<>();

    /**
     * Get the cineplex details from the cineplexHashMap
     * @param name of the cineplex
     * @return cineplex name
     */
    
    public Cineplex GetCineplex(String name){
        if(cineplexHashMap.containsKey(name)) return cineplexHashMap.get(name);
        return null;
    }
    
    /**
     * Get a list of cineplexes
     * @return list of cineplexes
     */

    public List<Cineplex> getCineplexes(){
        return new ArrayList<>(cineplexHashMap.values());
    }
    
    /**
     * Create a cineplex
     * @param name of the cineplex
     * @return cineplex object
     */

    public Cineplex createCineplex(String name){
        if(!cineplexHashMap.containsKey(name)){
            Cineplex c = new Cineplex(name);
            cineplexHashMap.put(name, c);
            return c;
        }
        return null;
    }
    
    /**
     * Serialize cineplex list
     * @return serialised string
     */
    //TODO
    @Override
    public String toSerialisedString() {
        List<Cineplex> cineplexList = new ArrayList<>(cineplexHashMap.values());
        return serialise(
          serialiseList(cineplexList, "cineplexes")
        );
    }

    
    /** Deserialize cineplex list
     * 
     */
    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        _static_manager = this;
        HashMap<String, String> pairs = deserialise(s);
        assert pairs != null;
        List<Cineplex> cineplexList = deserialiseList(Cineplex.class, pairs.get("cineplexes"));
        for(Cineplex cineplex: cineplexList){
            cineplexHashMap.put(cineplex.getCineplexName(), cineplex);
        }
        return getInstance();
    }

    /**
     * Create a static CineplexRepository object
     */
    private static CineplexRepository _static_manager = new CineplexRepository();

    
    /**
     * Get the instance of CineplexRepository
     * @return CineplexRepository object
     */
    
    public static CineplexRepository getInstance(){
        if(_static_manager == null){
            _static_manager = new CineplexRepository();
        }
        return _static_manager;
    }
    /**
     * CineplexRepository constructor
     */
    public CineplexRepository(){
        _static_manager = this;
    }
    /**
      * Remove a cineplex from the cineplex hashmap
      * @param name of the cineplex
      * @return boolean value
      */
    public boolean removeCineplex(String name) {
        if(cineplexHashMap.containsKey(name)){
            cineplexHashMap.get(name).RemoveCineplex();
            cineplexHashMap.remove(name);
            return true;
        }
        return false;
    }
}
