package main.com.entities;


import main.com.entities.Cinema;
import main.com.utils.ISerialisable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

import static main.com.utils.SerialisationUtils.*;

public class Cineplex implements ISerialisable {
	
	/** hashmap of cinemas
     */
	private HashMap<String, Cinema> cinemas;
	
	/** Name of cineplex
     */
    private String cineplexName;
     
    public Cineplex(){}
    
	/** Create a cineplex with the given name and list of cinemas
	 * @param cineplexName 		name of cineplex
     */
    public Cineplex(String cineplexName){
    	this.cineplexName = cineplexName;
    	this.cinemas = new HashMap<>();
	}
    
	/** Assigning cinemas to cineplex
	 * @param cineplexName	name of cineplex
	 * @param cinemas		list of cinemas
     */
	public Cineplex(String cineplexName, List<Cinema> cinemas) {
		this.cinemas = new HashMap<>();
		for(Cinema i: cinemas) this.cinemas.put(i.getName(), i);
		this.cineplexName = cineplexName;
	}
	
	/** Get a list of cinemas
	 * @return a list of cinemas
     */
	public List<Cinema> getCinemas() {
		return new ArrayList<>(cinemas.values());
	}

	/** Get cineplex name
	 * @return True/False if cinema is successfully removed
     */
    public String getCineplexName() {
		return cineplexName;
	}

	/** Get cinema
	 * @param key 		name of cinema
	 * @return name of cinema
     */
	public Cinema getCinema(String key){
    	if(cinemas.containsKey(key)) return cinemas.get(key);
    	return null;
	}

	/** Method to remove cineplex
     */
	public void RemoveCineplex(){
    	for(Cinema c:cinemas.values()){
    		c.RemoveCinema();
		}
	}

	 /**
     * Serialize cinema list
     * @return serialised string
     */
	@Override
	public String toSerialisedString() {
    	List<Cinema> cinemaList = new ArrayList<>(cinemas.values());
    	return serialise(
    			serialiseString(cineplexName, "name"),
				serialiseList(cinemaList, "cinemas")
		);
	}

	/** Deserialize cinema list
     */
	@Override
	public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
		HashMap<String, String> pairs = deserialise(s);
		assert pairs != null;
		List<Cinema> cinemaList = deserialiseList(Cinema.class, pairs.get("cinemas"));
		assert cinemaList != null;
		return new Cineplex(pairs.get("name"), cinemaList);
	}

	/** Method to Add cinema
	 * @param name 		name of cinema
	 * @param type		type of cinema
	 * @param s			seating of cinema
	 * @return cinema object
     */
	public Cinema addCinema(String name, CinemaType type, Seating s) {
    	if(cinemas.containsKey(name)){
    		return null;
		}
    	cinemas.put(name, new Cinema(name, this.cineplexName, type, s));
    	return cinemas.get(name);
	}
	
	/** Method to remove cinema
	 * @param name 		name of cinema
	 * @return True/False if cinema is successfully removed
     */
	public boolean removeCinema(String name) {
    	if(!cinemas.containsKey(name)) return false;
    	cinemas.get(name).RemoveCinema();
    	cinemas.remove(name);
    	return true;
	}
}