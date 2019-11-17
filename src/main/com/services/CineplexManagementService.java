package main.com.services;

import main.com.entities.Cinema;
import main.com.entities.CinemaType;
import main.com.entities.Cineplex;
import main.com.entities.Seating;
import main.com.repositories.CineplexRepository;

/** 
 * Admin staff uses this class to add/remove cineplexes/cinemas
 */
public class CineplexManagementService {
	/** 
     * Create a cineplex repository
     */
    private CineplexRepository _repo = CineplexRepository.getInstance();
    
    /** 
     * Create a cineplex
     * @param name
     * @return this cineplex's name
     */
    public Cineplex addCineplex(String name){
        return _repo.createCineplex(name);
    }
    
    /** 
     * Remove cineplex
     * @param name			name of cineplex
     * @return True/False if the cineplex is successfully added
     */
    public boolean RemoveCineplex(String name){
        return _repo.removeCineplex(name);
    }
    
    /** 
     * Create a cinema
     * @param cineplex 		name of cinplex that the cinema is under
     * @param name			name of this cinema
     * @param type 			type of this cinema
     * @param s 			seating of this cinema
     * @return this cineplex's name
     */
    public Cinema AddCinema(Cineplex cineplex, String name, CinemaType type, Seating s){
        return cineplex.addCinema(name, type, s);
    }
    
    /** 
     * Remove cinema
     * @param cineplex 		name of cinplex that the cinema is under
     * @param name 			name of the cinema that admin staff wish to remove
     * @return True/False if the cinema is successfully added
     */
    public boolean RemoveCinema(Cineplex cineplex, String name){
        return cineplex.removeCinema(name);
    }
}