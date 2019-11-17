package main.com.services;

import main.com.entities.Cinema;
import main.com.entities.Cineplex;
import main.com.entities.MovieShowing;
import main.com.repositories.CineplexRepository;

import java.util.List;

/** 
 * This class helps user to search for cineplexes and cinemas
 * @author SS1 Group 6
 * @version 13
 */
public class CineplexQueryService{
	
	/** 
     * Create a cineplex repository
     */
    private CineplexRepository _repo = CineplexRepository.getInstance();

    /** 
     * Get the name of cineplex
     * @param name
     * @return this cineplex's name
     */
    public Cineplex GetCineplex(String name){
        return _repo.GetCineplex(name);
    }
    
    /** 
     * Get the list of cineplexes
     * @return list of cineplexes
     */
    public List<Cineplex> getCineplexes(){
        return _repo.getCineplexes();
    }
    
    /** 
     * Get the name of cinema
     * @param cinplex		name of cineplex
     * @param CinemaID
     * @return this cinema's name
     */
    public Cinema GetCinema(Cineplex cineplex, String CinemaID){
        return cineplex.getCinema(CinemaID);
    }
    
    /** 
     * Get the list of cinemas' name 
     * @param Cineplex 		name of cineplex
     * @return list of cinemas' name under this cineplex
     */
    public List<Cinema> GetCinemas(Cineplex Cineplex){
        return Cineplex.getCinemas();
    }

    /** 
     * Get the list of movie shows
     * @param cinema 		name of cinema
     * @return list of movies showing at this cinema
     */
    public List<MovieShowing> GetShows(Cinema cinema){
        return cinema.getShows();
    }
}