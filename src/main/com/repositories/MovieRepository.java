package main.com.repositories;

import main.com.entities.*;
import main.com.utils.IShowingsListener;
import main.com.utils.ISerialisable;
import main.com.utils.ShowingsEventBroadcaster;
import main.com.utils.StringIntPair;

import static main.com.utils.SerialisationUtils.*;

import java.util.*;
import java.util.stream.Collectors;

/** This class acts as a repository for storing movies 
 * @author SS1 Group 6
 * @version 13
 */
public class MovieRepository implements IShowingsListener, ISerialisable {
    
    /**
	 * Get the instance of MovieRepository object
	 * @return MovieRepository object
	 */
    
    public static MovieRepository getInstance(){
        if(_static_manager == null){
            _static_manager = new MovieRepository();
        }
        return _static_manager;
    }
    
    /**
     * Get a list of all the movies that match with the movieName 
     * @param movieName - name of the movie to be searched
     * @return a list of all movies that match with the movieName
     */

    public List<Movie> getMovie(String movieName){
        List<Movie> matches = new ArrayList<>();
        for(Movie m:MovieSales.keySet()){
            if(m.movie_title.toLowerCase().contains(movieName.toLowerCase())){
                matches.add(m);
            }
        }
        return matches;
    }
    
     /**
     * Get a list of top movies 
     * @param number - is the number of top movies to be returned
     * @return a list of top movies
     */

    public List<Movie> getMovieByRating(int number){
        List<Movie> matches = new ArrayList<>(MovieSales.keySet());
        matches.sort((x,y)-> (int)(y.calculateOverallRating() - x.calculateOverallRating()));
        if(matches.size() >= number) return matches.subList(0, number);
        else return matches;
    }

    /**
     * Get a list of movies based on their popularity
     * @param number - is the number of popular movies to be returned
     * @return a list of popular movies
     */
    public List<Movie> getMovieByPopularity(int number){
        List<Movie> matches = new ArrayList<>(MovieSales.keySet());
        matches.sort((x,y)-> MovieSales.get(y).get() - MovieSales.get(x).get());
        if(matches.size() >= number) return matches.subList(0, number);
        else return matches;
    }
    /**
     * Get a list of showtimes for a particular movie
     * @param movie - movie who's show time is to be returned
     * @return a list of showtimes for a particular movie
     */

    public List<MovieShowing> getShowtimes(Movie movie){
        return MovieShowings.get(movie);
    }
    
    /**
     * Get a movie based on id
     * @param id
     * @return a movie who's id  matches with the searched id
     */


    public Movie getMovieByID(String id){
        for(Movie m:MovieSales.keySet()){
            if(m.getUUID().equals(id)){
                return m;
            }
        }
        throw new NoSuchElementException("Invalid Movie ID");
    }
    
    /**
     * Add a movie 
     * @param title - title of the movie
     * @param duration - duration of the movie
     * @param rating - rating certification of the movie
     * @param status - status of the movie(Showing , NotShowing, etc.)
     * @param synopsis - synopsis of the movie
     * @param director - director of the movie
     * @param cast - list of cast members involved in the movie
     * @return a movie object that has been created
     */

    public Movie addMovie(String title, int duration, RatingEnum rating, StatusEnum status, String synopsis, String director, List<String> cast){
        Movie toAdd = new Movie(title, duration, rating, status, synopsis, director, cast);
        addMovieToRepo(toAdd);
        return toAdd;
    }

    
     /**
     * Remove a movie from the system
     * @param movie - movie object that contains the details of the movie
     * @return a boolean value to verify if the movie has been removed successfully
     */
    public boolean removeMovie(Movie movie){
        if(!MovieSales.containsKey(movie)) return false;
        MovieSales.remove(movie);
        for (MovieShowing showing: MovieShowings.get(movie)){
            ShowingsEventBroadcaster.DestroyShowingEvent(showing, this);
        }
        MovieShowings.remove(movie);
        return true;
    }
    
    /**Event hook for creation of MovieShowing event
     * 
     */

    @Override
    public void OnShowingCreateEvent(MovieShowing showing, Object caller) {
        if(caller == this) return;
        MovieShowings.computeIfAbsent(showing.getShownMovie(), k -> new ArrayList<>());
        MovieShowings.get(showing.getShownMovie()).add(showing);
    }

     /**
     *  Event hook for destroying of MovieShowing event
     */
    @Override
    public void OnShowingDestroyEvent(MovieShowing showing, Object caller) {
        if(caller == this) return;
        if(MovieShowings.containsKey(showing.getShownMovie())) MovieShowings.get(showing.getShownMovie()).remove(showing);
    }
    
    /**
     * Serialize a String
     */

    @Override
    public String toSerialisedString() {
        List<StringIntPair> hashmapPairs = MovieSales.entrySet()
                .stream()
                .map((x) -> new StringIntPair(x.getKey().getUUID(), x.getValue().get()))
                .collect(Collectors.toList());
        List<Movie> movies = new ArrayList<>(MovieSales.keySet());
        return serialise(serialiseList(hashmapPairs, "MovieSales"), serialiseList(movies, "Movies"));
    }

    public boolean addSales(Movie movie, int sales){
        if(!MovieSales.containsKey(movie))return false;
        MovieSales.get(movie).increment(sales);
        return true;
    }

      /**
     * Deserialize a String
     * @param s- String to be deserialized
     * 
     */
    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        _static_manager = this;
        HashMap<String, String> pairs = deserialise(s);
        assert pairs != null;
        List<Movie> movies = deserialiseList(Movie.class, pairs.get("Movies"));
        Map<String, Integer> sales = deserialiseList(StringIntPair.class, pairs.get("MovieSales")).stream().collect(HashMap::new, (m, v) -> m.put(v.First(), v.Second()), HashMap::putAll);
        for(Movie m:movies){
            int count = 0;
            if(sales.containsKey(m.getUUID())){
                count = sales.get(m.getUUID());
            }
           addMovieToRepo(m, count);
        }
        return getInstance();
    }

    
    /**
     * Creation of MovieRepository object
     * 
     */
    private static MovieRepository _static_manager = new MovieRepository();
     /**
     * Creation of movieshowings hashmap
     */
    private HashMap<Movie, List<MovieShowing>> MovieShowings = new HashMap<>();
    /**
     * Creation of moviesales hashmap
     */
    private HashMap<Movie, SalesCounter> MovieSales = new HashMap<>();
    
    /** MovieRepository constructor
     * 
     */

    public MovieRepository(){
        _static_manager = this;
        ShowingsEventBroadcaster.AddListener(this);
    }
    
     /**
     * Add a movie 
     * @param movie- movie object that stores the details of the movie
     * @param count- count the sales of a movie
     * @return boolean value to verify if the movie has been added
     */

    private boolean addMovieToRepo(Movie movie, int count){
        MovieShowings.put(movie, new ArrayList<>());
        MovieSales.put(movie, new SalesCounter(count));
        return true;
    }
    
    /**
     * calls the addMovieToRepo function in order to add a movie and initialise the sales to 0
     * @param movie
     * @return
     */
    private boolean addMovieToRepo(Movie movie){
        return addMovieToRepo(movie, 0);
    }
}
