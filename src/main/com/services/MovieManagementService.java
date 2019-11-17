package main.com.services;

import main.com.entities.Movie;
import main.com.entities.RatingEnum;
import main.com.entities.StatusEnum;
import main.com.repositories.MovieRepository;

import java.util.List;

/** 
 * Admin staff uses this class to add/remove movies and change the details of each movie
 * @author SS1 Group 6
 * @version 13
 */
 

public class MovieManagementService {
	/** 
     * Create a movie repository
     */
    private MovieRepository _movieRepo = MovieRepository.getInstance();

    /** 
     * MovieManagementService constructor
     */
    public MovieManagementService(){
    }

    /** 
     * Add movie
     * @param title		title of movie
     * @param duration	duration of movie
     * @param rating 	rating of movie
     * @param status	showing status of movie
     * @param synopsis 	sypnopsis of movie
     * @param director 	director of movie
     * @param cast		list of casts of this movie
     * @return this movie
     */
    public Movie addMovie(String title, int duration, RatingEnum rating, StatusEnum status, String synopsis, String director, List<String> cast){
        return _movieRepo.addMovie(title, duration, rating, status, synopsis, director, cast);
    }

    /** 
     * Changes the showing status of this movie
     * @param name		name of movie
     * @param status	new showing status of this movie
     * @return True/False if the status of the movie is successfully changed
     */
    public boolean setMovieStatus(Movie movie, StatusEnum status){
        if(movie == null) return false;
        movie.movieStatus = status;
        return true;
    }

    /** 
     * Changes the movie rating of this movie
     * @param name		name of movie
     * @param rating	rating of this movie
     * @return True/False if the director of the movie is successfully changed
     */
    public boolean setMovieRating(Movie movie, RatingEnum rating){
        if(movie == null) return false;
        movie.rating = rating;
        return true;
    }

    /** 
     * To remove movie
     * @param name		name of movie
     * @return True/False if the movie is successfully removed
     */
    public boolean removeMovie(Movie movie){
        return _movieRepo.removeMovie(movie);
    }

    /** 
     * Get the list of movie
     * @param name		name of movie
     * @return the list of movies that matches the input
     */
    public List<Movie> getMovie(String name){
        return _movieRepo.getMovie(name);
    }

    /** 
     * Changes the synopsis of this movie
     * @param name		name of movie
     * @param synopsis	synopsis of this movie
     * @return True/False if the synopsis of the movie is successfully changed
     */
    public boolean setMovieSynopsis(Movie movie, String synopsis){
        if(movie == null) return false;
        movie.movie_synopsis = synopsis;
        return true;
    }

    /** 
     * Changes the movie director of this movie
     * @param name		name of movie
     * @param director	director of the movie
     * @return True/False if the rating of the movie is successfully changed
     */
    public boolean setMovieDirector(Movie movie, String director){
        if(movie == null) return false;
        movie.movie_director = director;
        return true;
    }

    /** 
     * Add movie cast
     * @param name		name of movie
     * @param cast		name of cast
     * @return True/False if movie cast is successfully added
     */
    public boolean addMovieCast(Movie movie, String cast){
        if(movie == null) return false;
        if(movie.cast.contains(cast)) return true;
        movie.cast.add(cast);
        return true;
    }

    /** 
     * To remove movie cast
     * @param name		name of movie
     * @param cast		nama of casts that staff wish to remove
     * @return True/False if the cast is successfully removed
     */
    public boolean removeMovieCast(Movie movie, String cast){
        if(movie == null) return false;
        if(!movie.cast.contains(cast)) return false;
        movie.cast.remove(cast);
        return true;
    }
    
    /** 
     * To remove review
     * @param name		name of movie
     * @param index 	index of the review that staff wish to remove
     * @return True/False if the review is successfully removed
     */
    public boolean removeReview(Movie movie, int index){
        if (movie.removeReview(index)) return true;
        return false;
    }
}
