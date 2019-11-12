package main.com.entities;

import main.com.repositories.MovieRepository;
import main.com.utils.ISerialisable;
import main.com.utils.SerialisationUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

public class MovieShowing implements ISerialisable {
    private Movie movie;
    private LocalDateTime showing_time;
    //TODO: Change duration to be from inside movie
    private int duration_min;
    private Seating seatingplan;
    private String _movieID;

    public MovieShowing(){}

    public MovieShowing(Movie movie, LocalDateTime showing_time, int duration_min, Seating seatingplan) {
        this.movie = movie;
        this._movieID = movie.getUUID();
        this.showing_time = showing_time;
        this.duration_min = duration_min;
        this.seatingplan = seatingplan;
    }

    private MovieShowing(String movieID, LocalDateTime showing_time, int duration_min, Seating seatingplan) {
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
            movie = MovieRepository.getInstance().getMovieByID(_movieID);
        }
        return movie;
    }

    @Override
    public String toSerialisedString(){
        return SerialisationUtils.serialise(
                SerialisationUtils.serialiseString(movie.getUUID(), "movie"),
                SerialisationUtils.serialiseDateTime(showing_time, "datetime"),
                SerialisationUtils.serialiseInt(duration_min, "duration"),
                SerialisationUtils.serialiseObject(seatingplan, "seatingplan")
        );
    }

    @Override
    public MovieShowing fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        HashMap<String, String> pairs = SerialisationUtils.deserialise(s);
        try{
            assert pairs != null;
            String movie_ID = SerialisationUtils.deserialiseString(pairs.get("movie"));
            Seating seat = SerialisationUtils.deserialiseObject(Seating.class, pairs.get("seatingplan"));
            int duration = SerialisationUtils.deserialiseInt(pairs.get("duration"));
            LocalDateTime time = SerialisationUtils.deserialiseDateTime(pairs.get("datetime"));
            Movie movie = MovieRepository.getInstance().getMovieByID(movie_ID);
            if(movie == null) return new MovieShowing(movie_ID, time, duration, seat);
            return new MovieShowing(movie, time, duration, seat);
        }catch(Exception e){
            throw new InvalidPropertiesFormatException("");
        }
    }
}
