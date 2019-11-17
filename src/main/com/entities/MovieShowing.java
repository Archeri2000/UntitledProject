package main.com.entities;
import main.com.entities.Seating;

import main.com.repositories.MovieRepository;
import main.com.utils.ISerialisable;
import static main.com.utils.SerialisationUtils.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

/** This class holds the details of a movie showing
 * @author SS1 Group 6
 * @version 13
 */
public class MovieShowing implements ISerialisable {
	 /** name of this movie
     */
    private Movie movie;
    
	 /** showing time of the movie
     */
    private LocalDateTime showing_time;
    
	 /** seating plan of the movie at a cinema
     */
    private Seating seatingplan;
    
	 /** movie ID of a movie
     */
    private String _movieID;
    
	 /** this movie type
     */
    private ShowingEnum showType;
    
	 /** this cinema type
     */
    private CinemaType cinemaType;
    
	 /** name of this cinplex
     */
    private String cineplex;
    
	 /** name of this cinema
     */
    private String cinema;

    public MovieShowing(){}

    /** Creates a new movie showing with the details
     * @param movie				name of the movie
     * @param showting_time		showing time of the movie
     * @param seating plan		seating plan of the movie at a cinema
     * @param showtype			movie type
     * @param cinemaType		type of cinema
     * @param cinema			name of cinema
     * @param cineplex			name of cineplex
     */
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

    /** Creates a new movie showing with the details
     * @param movieID			ID of the movie
     * @param showting_time		showing time of the movie
     * @param seating plan		seating plan of the movie at a cinema
     * @param showtype			movie type
     * @param cinemaType		type of cinema
     * @param cinema			name of cinema
     * @param cineplex			name of cineplex
     */
    private MovieShowing(String movieID, LocalDateTime showing_time, Seating seatingplan, ShowingEnum showtype, CinemaType cinemaType, String cineplex, String cinema) {
        this._movieID = movieID;
        this.showing_time = showing_time;
        this.seatingplan = seatingplan;
        this.showType = showtype;
        this.cinemaType = cinemaType;
        this.cinema = cinema;
        this.cineplex = cineplex;
    }
    
    /** Get Cineplex
     * @return name of cineplex
     */
    public String getCineplex(){
    	return cineplex;
    }

    /** Get cinema
     * @return name of cinema
     */
    public String getCinema() {
        return cinema;
    }

    /** Get showing time
     * @return showing time
     */
    public LocalDateTime getShowing_time(){
        return showing_time;
    }

    /** Get seating plan
     * @return seating plan
     */
    public Seating getSeatingplan(){
    	return seatingplan;
    }

    /** Get ending time
     * @return ending time
     */
    public LocalDateTime getEnding_time(){
        return showing_time.plusMinutes(movie.durationMin);
    }

    /** Get shown movie
     * @return movie object
     */
    public Movie getShownMovie(){
        if(movie == null){
            movie = MovieRepository.getInstance().getMovieByID(_movieID);
        }
        return movie;
    }

    /** Get shown movie title
     * @return name of movie
     */
    public String getShownMovieTitle(){
        if(movie == null){
            movie = MovieRepository.getInstance().getMovieByID(_movieID);
        }
        return movie.movie_title;
    }

    /** Get cinema type
     * @return cinema type
     */
    public CinemaType getCinemaType() {
        return cinemaType;
    }

    /** Get show type
     * @return show type
     */
    public ShowingEnum getShowType() {
        return showType;
    }

    /**
     * Serialize movie showing
     * @return serialised string
     */
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

    /** Deserialize movie showing
     */
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