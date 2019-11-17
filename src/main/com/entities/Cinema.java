package main.com.entities;

import main.com.utils.ISerialisable;
import main.com.utils.IShowingsListener;
import static main.com.utils.SerialisationUtils.*;
import main.com.utils.ShowingsEventBroadcaster;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

public class Cinema implements ISerialisable, IShowingsListener {

	private CinemaType cinemaType;
	private String cineplex;
	private String name;
	private Seating seating;
	private List<MovieShowing> movieSchedule = new ArrayList<>();

	public Cinema(){}

	public Cinema(String name, String cineplex, CinemaType cinemaType, Seating s, List<MovieShowing> schedule) {
		this.name = name;
		this.cineplex = cineplex;
		this.cinemaType = cinemaType;
		this.movieSchedule = schedule;
		this.seating = s;
		for(MovieShowing showing: schedule){
		    ShowingsEventBroadcaster.CreateShowingEvent(showing, this);
        }
		ShowingsEventBroadcaster.AddListener(this);
	}

	public Cinema(String name, String cineplex, CinemaType type, Seating s) {
		this.name = name;
		this.cineplex = cineplex;
		this.cinemaType = type;
		this.seating= s;
		ShowingsEventBroadcaster.AddListener(this);
	}

	public void RemoveCinema(){
		for(MovieShowing s:movieSchedule){
			removeMovieShowing(s);
		}
		ShowingsEventBroadcaster.RemoveListener(this);
	}

	public Seating getSeats(){ return seating; }

	public String getName() {
		return name;
	}

	public String getCineplex(){ return cineplex; }
	
	public CinemaType getCinemaType(){
		return cinemaType;
	}

	public List<MovieShowing> getShows(){
		return movieSchedule;
	}

	public MovieShowing addMovieShowing(Movie movie, LocalDateTime screening_time, ShowingEnum showtype){
		if(isTimeslotAvailable(screening_time, movie.durationMin)){
			//TODO: Fix
			seating = new Seating();
			MovieShowing showing = new MovieShowing(movie, screening_time, seating, showtype);
			movieSchedule.add(showing);
			ShowingsEventBroadcaster.CreateShowingEvent(showing, this);
			return showing;
		}
		return null;
	}

	public boolean removeMovieShowing(MovieShowing showing){
		if(movieSchedule.contains(showing)){
			movieSchedule.remove(showing);
			ShowingsEventBroadcaster.DestroyShowingEvent(showing, this);
			return true;
		}
		return false;
	}

	private boolean isTimeslotAvailable(LocalDateTime start_time, int duration_min){
		for(MovieShowing m: movieSchedule) {
			if(m.getEnding_time().isAfter(start_time)&&
					m.getShowing_time().isBefore(start_time.plusMinutes(duration_min))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toSerialisedString(){
		return serialise(
				serialiseObject(seating, "seating"),
				serialiseString(cinemaType.name(), "type"),
				serialiseString(name, "cinemaName"),
				serialiseString(cineplex, "cineplex"),
				serialiseList(movieSchedule, "showings"));
	}

	@Override
	public Cinema fromSerialisedString(String s) throws InvalidPropertiesFormatException {
		HashMap<String, String> pairs = deserialise(s);
		try{
			assert pairs != null;
			Seating seat = deserialiseObject(Seating.class, pairs.get("seating"));
			List<MovieShowing> schedule = deserialiseList(MovieShowing.class, pairs.get("showings"));
			CinemaType type = CinemaType.valueOf(deserialiseString(pairs.get("type")));
			String name = deserialiseString(pairs.get("cinemaName"));
			String cineplex = deserialiseString(pairs.get("cineplex"));
			return new Cinema(name, cineplex, type, seat, schedule);
		}catch(Exception e){
			throw new InvalidPropertiesFormatException("");
		}
	}

	@Override
	public void OnShowingCreateEvent(MovieShowing showing, Object caller) {
	}

	@Override
	public void OnShowingDestroyEvent(MovieShowing showing, Object caller) {
		if(caller == this) return;
		if(movieSchedule.contains(showing)) movieSchedule.remove(showing);
	}
}
