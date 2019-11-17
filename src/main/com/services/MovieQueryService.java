package main.com.services;

import main.com.entities.Movie;
import main.com.entities.MovieShowing;
import main.com.repositories.MovieRepository;

import java.util.List;


/** 
 * This class helps a customer to search movies
 * @author SS1 Group 6
 * @version 13
 */
public class MovieQueryService{
	
	/** 
     * Create a movie repository
     */
    private MovieRepository _repo = MovieRepository.getInstance();

    /** 
     * Get the list of movies
     * @param name 		name of movie that customer wish to search
     * @return list of movies that matches customer's input
     */
    public List<Movie> getMovie(String Movie){
       return _repo.getMovie(Movie);
    }

    /** 
     * Get the list of movies
     * @param number 	number of top rated movies that customer wish to search
     * @return list of top rated movies 
     */
    public List<Movie>getTopRatedMovie(int number){
        return _repo.getMovieByRating(number);
    }

    /** 
     * Get the list of movies
     * @param number 	number of popular movies that customer wish to search
     * @return list of popular movies 
     */
    public List<Movie> getPopularMovies(int number){
        return _repo.getMovieByPopularity(number);
    }
    
    /** 
     * @param name		name of movie that the customer wish to give review and rating on
     * @param review	review that the customer wish to give the movie
     * @param rating  	rating that the customer wish to give the movie
     */
    public void addReviews(Movie movie, String review, double rating){
        movie.addReview(review, rating);
    }
    
    /** 
     * Get showtime timeslots
     * @param name 		name of movie that customer wish to watch
     * @return list of showtimes
     */
    public List<MovieShowing> getShowtimeTimeslots(Movie movie){
        return _repo.getShowtimes(movie);
    }
}