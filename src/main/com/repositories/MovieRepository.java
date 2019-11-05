package main.com.repositories;

import main.com.entities.Movie;
import main.com.utils.IScheduleListener;
import main.com.entities.MovieTimeslot;
import main.com.utils.ISerialisable;

import java.util.*;

public class MovieRepository implements IScheduleListener, ISerialisable {
    public static MovieRepository getInstance(){
        if(_static_manager == null){
            _static_manager = new MovieRepository();
        }
        return _static_manager;
    }

    public List<Movie> getMovie(String movieName){
        List<Movie> matches = new ArrayList<>();
        for(Movie m:MovieSales.keySet()){
            if(m.movie_title.toLowerCase().contains(movieName.toLowerCase())){
                matches.add(m);
            }
        }
        return matches;
    }

    public List<Movie> getMovieByRating(int number){
        List<Movie> matches = new ArrayList<>(MovieSales.keySet());
        matches.sort((x,y)-> (int)(y.calculateOverallRating() - x.calculateOverallRating()));
        if(matches.size() >= number) return matches.subList(0, number);
        else return matches;
    }

    public List<Movie> getMovieByPopularity(int number){
        List<Movie> matches = new ArrayList<>(MovieSales.keySet());
        matches.sort((x,y)-> MovieSales.get(y) - MovieSales.get(x));
        if(matches.size() >= number) return matches.subList(0, number);
        else return matches;
    }

    public List<MovieTimeslot> getShowtimes(Movie movie){
        return MovieShowings.get(movie);
    }

    public Movie getMovieByID(String id){
        for(Movie m:MovieSales.keySet()){
            if(m.getUUID() == id){
                return m;
            }
        }
        throw new NoSuchElementException("Invalid Movie ID");
    }

    public Movie addMovie(String title, int duration, RatingEnum rating, StatusEnum status, String synopsis, String director, String[]cast){
        Movie toAdd = new Movie(title, duration, rating, status, synopsis, director, cast);
    }

    @Override
    public void OnScheduleCreateEvent(MovieTimeslot timeslot) {
        MovieShowings.get(timeslot.getShownMovie()).add(timeslot);
    }

    @Override
    public void OnScheduleDestroyEvent(MovieTimeslot timeslot) {
        MovieShowings.get(timeslot.getShownMovie()).remove(timeslot);
    }

    @Override
    public String toSerialisedString() {
        return null;
    }

    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        return null;
    }

    private static MovieRepository _static_manager = new MovieRepository();
    private HashMap<Movie, List<MovieTimeslot>> MovieShowings = new HashMap<>();
    private HashMap<Movie, Integer> MovieSales = new HashMap<>();
    private MovieRepository(){
    }
}
