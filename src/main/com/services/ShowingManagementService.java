package main.com.services;

import main.com.entities.*;
import main.com.repositories.MovieRepository;

import java.time.LocalDateTime;
import java.util.List;

/** 
 * Admin staff uses this class to manage movie showing
 * @author SS1 Group 6
 * @version 13
 */
public class ShowingManagementService {
	/** 
     * Create a movie repository
     */
    private MovieRepository _movieRepo = MovieRepository.getInstance();

    /** 
     * MovieManagementService constructor
     */
    public ShowingManagementService(){
    }

    /** 
     * Add movie showing
     * @param cinema		name of cinema that will be showing this movie
     * @param movie			name of movie to be added
     * @param starTime		the starting time of this movie
     * @param showtype		the type of movie
     * @return movie showing
     */
    public MovieShowing addShowing(Cinema cinema, Movie movie, LocalDateTime startTime, ShowingEnum showtype){
        return cinema.addMovieShowing(movie, startTime, showtype);
    }
    
    /** 
     * Remove showing
     * @param cinema		name of cinema
     * @param showing		movie showing that's to be removed
     * @return True/False if the movie showing is successfully removed
     */
    public boolean removeShowing(Cinema cinema, MovieShowing showing){
        return cinema.removeMovieShowing(showing);
    }
}