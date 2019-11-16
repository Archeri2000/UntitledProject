package main.com.entities;


import main.com.entities.Cinema;
import main.com.utils.ISerialisable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

import static main.com.utils.SerialisationUtils.*;

public class Cineplex implements ISerialisable {
	
	private HashMap<String, Cinema> cinemas;
    private String cineplexName;
     
    public Cineplex(){}
    public Cineplex(String cineplexName){
    	this.cineplexName = cineplexName;
    	this.cinemas = new HashMap<>();
	}
	public Cineplex(String cineplexName, List<Cinema> cinemas) {
		this.cinemas = new HashMap<>();
		for(Cinema i: cinemas) this.cinemas.put(i.getName(), i);
		this.cineplexName = cineplexName;
	}
	
	public List<Cinema> getCinemas() {
		return new ArrayList<>(cinemas.values());
	}

    public String getCineplexName() {
		return cineplexName;
	}

	public Cinema getCinema(String key){
    	if(cinemas.containsKey(key)) return cinemas.get(key);
    	return null;
	}

	public void RemoveCineplex(){
    	for(Cinema c:cinemas.values()){
    		c.RemoveCinema();
		}
	}

	@Override
	public String toSerialisedString() {
    	List<Cinema> cinemaList = new ArrayList<>(cinemas.values());
    	return serialise(
    			serialiseString(cineplexName, "name"),
				serialiseList(cinemaList, "cinemas")
		);
	}

	@Override
	public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
		HashMap<String, String> pairs = deserialise(s);
		assert pairs != null;
		List<Cinema> cinemaList = deserialiseList(Cinema.class, pairs.get("cinemas"));
		assert cinemaList != null;
		return new Cineplex(pairs.get("name"), cinemaList);
	}

	public Cinema addCinema(String name, CinemaType type, Seating s) {
    	if(cinemas.containsKey(name)){
    		return null;
		}
    	cinemas.put(name, new Cinema(name, this.cineplexName, type, s));
    	return cinemas.get(name);
	}

	public boolean removeCinema(String name) {
    	if(!cinemas.containsKey(name)) return false;
    	cinemas.get(name).RemoveCinema();
    	cinemas.remove(name);
    	return true;
	}
}
