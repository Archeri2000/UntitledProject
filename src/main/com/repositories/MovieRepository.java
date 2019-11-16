package main.com.repositories;

import main.com.entities.*;
import main.com.utils.IShowingsListener;
import main.com.utils.ISerialisable;
import main.com.utils.ShowingsEventBroadcaster;
import main.com.utils.StringIntPair;

import static main.com.utils.SerialisationUtils.*;

import java.util.*;
import java.util.stream.Collectors;

public class MovieRepository implements IShowingsListener, ISerialisable {
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
        matches.sort((x,y)-> MovieSales.get(y).get() - MovieSales.get(x).get());
        if(matches.size() >= number) return matches.subList(0, number);
        else return matches;
    }

    public List<MovieShowing> getShowtimes(Movie movie){
        return MovieShowings.get(movie);
    }

    public Movie getMovieByID(String id){
        for(Movie m:MovieSales.keySet()){
            if(m.getUUID().equals(id)){
                return m;
            }
        }
        throw new NoSuchElementException("Invalid Movie ID");
    }

    public Movie addMovie(String title, int duration, RatingEnum rating, StatusEnum status, String synopsis, String director, List<String> cast){
        Movie toAdd = new Movie(title, duration, rating, status, synopsis, director, cast);
        addMovieToRepo(toAdd);
        return toAdd;
    }

    public boolean removeMovie(Movie movie){
        if(!MovieSales.containsKey(movie)) return false;
        MovieSales.remove(movie);
        for (MovieShowing showing: MovieShowings.get(movie)){
            ShowingsEventBroadcaster.DestroyShowingEvent(showing, this);
        }
        MovieShowings.remove(movie);
        return true;
    }

    @Override
    public void OnShowingCreateEvent(MovieShowing showing, Object caller) {
        if(caller == this) return;
        MovieShowings.get(showing.getShownMovie()).add(showing);
    }

    @Override
    public void OnShowingDestroyEvent(MovieShowing showing, Object caller) {
        if(caller == this) return;
        MovieShowings.get(showing.getShownMovie()).remove(showing);
    }

    @Override
    public String toSerialisedString() {
        List<StringIntPair> hashmapPairs = MovieSales.entrySet()
                .stream()
                .map((x) -> new StringIntPair(x.getKey().getUUID(), x.getValue().get()))
                .collect(Collectors.toList());
        List<Movie> movies = new ArrayList<>(MovieSales.keySet());
        return serialise(serialiseList(hashmapPairs, "MovieSales"), serialiseList(movies, "Movies"));
    }

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

    private static MovieRepository _static_manager = new MovieRepository();
    private HashMap<Movie, List<MovieShowing>> MovieShowings = new HashMap<>();
    private HashMap<Movie, SalesCounter> MovieSales = new HashMap<>();

    public MovieRepository(){
        _static_manager = this;
        ShowingsEventBroadcaster.AddListener(this);
    }

    private boolean addMovieToRepo(Movie movie, int count){
        MovieShowings.put(movie, new ArrayList<>());
        MovieSales.put(movie, new SalesCounter(count));
        return true;
    }
    private boolean addMovieToRepo(Movie movie){
        return addMovieToRepo(movie, 0);
    }
}
