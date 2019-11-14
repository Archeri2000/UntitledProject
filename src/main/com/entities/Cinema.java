package main.com.entities;

import main.com.utils.ISerialisable;

import java.util.InvalidPropertiesFormatException;
import java.util.List;

public class Cinema implements ISerialisable {

	private CinemaType cinemaType;
	private String cineplex;
	private String name;
	private CinemaSchedule schedule;
	private Seating seating;

	public Cinema(){}

	public Cinema(String name, CinemaType cinemaType, Seating s, CinemaSchedule schedule) {
		this.name = name; 
		this.cinemaType = cinemaType;
		this.schedule = schedule;
	}

	public Cinema(String name, CinemaType type, Seating s) {
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public CinemaType getCinemaType(){
		return cinemaType;
	}
	
	public float getMultiplerBasedOnType() {
		return cinemaType.getMultiplier();
	}

	public List<MovieShowing> getShows(){
		//TODO
		return null;
	}

	@Override
	public String toSerialisedString() {
		return null;
	}

	@Override
	public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
		return null;
	}
}
