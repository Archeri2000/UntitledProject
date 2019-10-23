package main.com.moviebooking;

import main.com.serialisation.ISerialisable;
import main.com.serialisation.SerialisationDependencyManager;
import main.com.serialisation.SerialisationUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

public class MovieTimeslot implements ISerialisable {
    private Movie movie;
    private LocalDateTime showing_time;
    private int duration_min;
    private Seating seatingplan;
    private String _movieID;

    public MovieTimeslot(){}

    public MovieTimeslot(Movie movie, LocalDateTime showing_time, int duration_min, Seating seatingplan) {
        this.movie = movie;
        this.showing_time = showing_time;
        this.duration_min = duration_min;
        this.seatingplan = seatingplan;
    }

    private MovieTimeslot(String movieID, LocalDateTime showing_time, int duration_min, Seating seatingplan) {
        this._movieID = movieID;
        this.showing_time = showing_time;
        this.duration_min = duration_min;
        this.seatingplan = seatingplan;
    }

    public LocalDateTime getShowing_time(){
        return showing_time;
    }

    public LocalDateTime getEnding_time(){
        return showing_time.plusMinutes(duration_min);
    }

    public Movie getShownMovie(){
        if(movie == null){
            movie = SerialisationDependencyManager.getInstance().getMovieByID(_movieID);
        }
        return movie;
    }

    @Override
    public String toSerialisedString(){
        return SerialisationUtils.serialise(SerialisationUtils.serialiseString(movie.getUUID(), "movie"),
                SerialisationUtils.serialiseDateTime(showing_time, "datetime"),
                SerialisationUtils.serialiseInt(duration_min, "duration"),
                SerialisationUtils.serialiseObject(seatingplan, "seatingplan"));
    }

    @Override
    public MovieTimeslot fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        HashMap<String, String> pairs = SerialisationUtils.deserialise(s);
        try{
            String movie_ID = SerialisationUtils.deserialiseString(pairs.get("movie"));
            Seating seat = SerialisationUtils.deserialiseObject(Seating.class, pairs.get("seatingplan"));
            int duration = SerialisationUtils.deserialiseInt(pairs.get("duration"));
            LocalDateTime time = SerialisationUtils.deserialiseDateTime(pairs.get("datetime"));
            Movie movie = SerialisationDependencyManager.getInstance().getMovieByID(movie_ID);
            if(movie == null) return new MovieTimeslot(movie_ID, time, duration, seat);
            return new MovieTimeslot(movie, time, duration, seat);
        }catch(Exception e){
            throw new InvalidPropertiesFormatException("");
        }
    }
}
