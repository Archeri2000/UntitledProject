package main.com.entities;
import main.com.entities.Seating;

import main.com.repositories.MovieRepository;
import main.com.utils.ISerialisable;
import static main.com.utils.SerialisationUtils.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

public class MovieShowing implements ISerialisable {
    private Movie movie;
    private LocalDateTime showing_time;
    private Seating seatingplan;
    private String _movieID;
    private ShowingEnum showType;
    private CinemaType cinemaType;
    private String cineplex;
    private String cinema;

    public MovieShowing(){}

    public MovieShowing(Movie movie, LocalDateTime showing_time, Seating seatingplan, ShowingEnum showtype, CinemaType cinemaType, String cineplex, String cinema) {
        this.movie = movie;
        this._movieID = movie.getUUID();
        this.showing_time = showing_time;
        this.seatingplan = seatingplan;
        this.showType = showtype;
        this.cinemaType = cinemaType;
        this.cinema = cinema;
        this.cineplex = cineplex;
    }

    private MovieShowing(String movieID, LocalDateTime showing_time, Seating seatingplan, ShowingEnum showtype, CinemaType cinemaType, String cineplex, String cinema) {
        this._movieID = movieID;
        this.showing_time = showing_time;
        this.seatingplan = seatingplan;
        this.showType = showtype;
        this.cinemaType = cinemaType;
        this.cinema = cinema;
        this.cineplex = cineplex;
    }
    public String getCineplex(){return cineplex;}

    public String getCinema() {
        return cinema;
    }

    public LocalDateTime getShowing_time(){
        return showing_time;
    }

    public Seating getSeatingplan(){return seatingplan;}

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

    public CinemaType getCinemaType() {
        return cinemaType;
    }

    public ShowingEnum getShowType() {
        return showType;
    }

    @Override
    public String toSerialisedString(){
        return serialise(
                serialiseString(movie.getUUID(), "movie"),
                serialiseDateTime(showing_time, "datetime"),
                serialiseString(showType.name(), "showtype"),
                serialiseString(cinemaType.name(), "cinematype"),
                serialiseObject(seatingplan, "seatingplan"),
                serialiseString(cinema, "cinema"),
                serialiseString(cineplex, "cineplex")
        );
    }

    @Override
    public MovieShowing fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        HashMap<String, String> pairs = deserialise(s);
        try{
            assert pairs != null;
            String movie_ID = deserialiseString(pairs.get("movie"));
            Seating seat = deserialiseObject(Seating.class, pairs.get("seatingplan"));
            ShowingEnum showtype = ShowingEnum.valueOf(deserialiseString(pairs.get("showtype")));
            CinemaType cinemaType = CinemaType.valueOf(deserialiseString(pairs.get("cinematype")));
            LocalDateTime time = deserialiseDateTime(pairs.get("datetime"));
            String cinema = deserialiseString(pairs.get("cinema"));
            String cineplex = deserialiseString(pairs.get("cineplex"));
            Movie movie = MovieRepository.getInstance().getMovieByID(movie_ID);
            if(movie == null) return new MovieShowing(movie_ID, time, seat, showtype, cinemaType, cineplex, cinema);
            return new MovieShowing(movie, time, seat, showtype, cinemaType, cineplex, cinema);
        }catch(Exception e){
            throw new InvalidPropertiesFormatException("");
        }
    }

}
