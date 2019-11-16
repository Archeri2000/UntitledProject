package main.com.repositories;

import main.com.entities.Cineplex;
import main.com.utils.ISerialisable;

import java.util.*;

public class CineplexRepository implements ISerialisable {
    private HashMap<String, Cineplex> cineplexHashMap = new HashMap<>();

    public Cineplex GetCineplex(String name){
        if(cineplexHashMap.containsKey(name)) return cineplexHashMap.get(name);
        return null;
    }

    public List<Cineplex> getCineplexs(){
        return new ArrayList<>(cineplexHashMap.values());
    }

    public Cineplex createCineplex(String name){
        if(!cineplexHashMap.containsKey(name)){
            Cineplex c = new Cineplex(name);
            cineplexHashMap.put(name, c);
            return c;
        }
        return null;
    }
    //TODO
    @Override
    public String toSerialisedString() {
        return "asdf";
    }

    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        return getInstance();
    }

    private static CineplexRepository _static_manager = new CineplexRepository();

    public static CineplexRepository getInstance(){
        if(_static_manager == null){
            _static_manager = new CineplexRepository();
        }
        return _static_manager;
    }

    public CineplexRepository(){
        _static_manager = this;
    }

    public boolean removeCineplex(String name) {
        if(cineplexHashMap.containsKey(name)){
            cineplexHashMap.get(name).RemoveCineplex();
            cineplexHashMap.remove(name);
            return true;
        }
        return false;
    }
}
