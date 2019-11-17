package main.com.entities;
import main.com.entities.Seating;

import main.com.repositories.MovieRepository;
import main.com.utils.ISerialisable;
import main.com.utils.SerialisationUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

public class MovieShowing implements ISerialisable {
    private Movie movie;
    private LocalDateTime showing_time;
    private List<Seat> seatingplan;
    private String _movieID;
    private ShowingEnum showType;

    public MovieShowing(){}

    public MovieShowing(Movie movie, LocalDateTime showing_time, List<Seat> seatingplan, ShowingEnum showtype) {
        this.movie = movie;
        this._movieID = movie.getUUID();
        this.showing_time = showing_time;
        this.seatingplan = seatingplan;
        this.showType = showtype;
    }

    private MovieShowing(String movieID, LocalDateTime showing_time, List<Seat> seatingplan, ShowingEnum showtype) {
        this._movieID = movieID;
        this.showing_time = showing_time;
        this.seatingplan = seatingplan;
        this.showType = showtype;
    }

    public LocalDateTime getShowing_time(){
        return showing_time;
    }

    public LocalDateTime getEnding_time(){
        return showing_time.plusMinutes(movie.durationMin);
    }

    public Movie getShownMovie(){
        if(movie == null){
            movie = MovieRepository.getInstance().getMovieByID(_movieID);
        }
        return movie;
    }

    public String getShownMovieTitle(){
        if(movie == null){
            movie = MovieRepository.getInstance().getMovieByID(_movieID);
        }
        return movie.movie_title;
    }

    @Override
    public String toSerialisedString(){
        return SerialisationUtils.serialise(
                SerialisationUtils.serialiseString(movie.getUUID(), "movie"),
                SerialisationUtils.serialiseDateTime(showing_time, "datetime"),
                SerialisationUtils.serialiseString(showType.name(), "showtype"),
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
            ShowingEnum showtype = ShowingEnum.valueOf(SerialisationUtils.deserialiseString(pairs.get("showtype")));
            LocalDateTime time = SerialisationUtils.deserialiseDateTime(pairs.get("datetime"));
            Movie movie = MovieRepository.getInstance().getMovieByID(movie_ID);
            if(movie == null) return new MovieShowing(movie_ID, time, seat, showtype);
            return new MovieShowing(movie, time, seat, showtype);
        }catch(Exception e){
            throw new InvalidPropertiesFormatException("");
        }
    }
}
