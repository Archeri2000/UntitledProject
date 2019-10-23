package main.com.moviebooking;

public class Cinema {

	private CinemaType cinemaType;
	private String cineplex;
	private String name;
	private CinemaSchedule schedule;
	
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
