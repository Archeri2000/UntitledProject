package main.com.entities;

public class Cinema implements ISerialisable {

	private CinemaType cinemaType;
	private String cineplex;
	private String ID;
	private String name;
	private CinemaSchedule schedule;

	public Cinema(){}

	public Cinema(String name, CinemaType cinemaType, CinemaSchedule schedule) {
		this.name = name; 
		this.cinemaType = cinemaType;
		this.schedule = schedule;
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
	
}
