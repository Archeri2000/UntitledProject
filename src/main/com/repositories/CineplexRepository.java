package main.com.repositories;

import main.com.entities.Cineplex;
import main.com.entities.Movie;
import main.com.utils.ISerialisable;
import main.com.utils.StringIntPair;

import java.util.*;

import static main.com.utils.SerialisationUtils.*;
import static main.com.utils.SerialisationUtils.deserialiseList;

public class CineplexRepository implements ISerialisable {
    private HashMap<String, Cineplex> cineplexHashMap = new HashMap<>();

    public Cineplex GetCineplex(String name){
        if(cineplexHashMap.containsKey(name)) return cineplexHashMap.get(name);
        return null;
    }

    public List<Cineplex> getCineplexes(){
        return new ArrayList<>(cineplexHashMap.values());
    }

    public Cineplex CreateCineplex(String name){
        if(!cineplexHashMap.containsKey(name)){
            Cineplex c = new Cineplex(name);
        }
        return null;
    }
    //TODO
    @Override
    public String toSerialisedString() {
        List<Cineplex> cineplexList = new ArrayList<>(cineplexHashMap.values());
        return serialise(
          serialiseList(cineplexList, "cineplexes")
        );
    }

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
