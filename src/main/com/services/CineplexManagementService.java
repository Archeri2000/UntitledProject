package main.com.services;

import main.com.entities.Cinema;
import main.com.entities.CinemaType;
import main.com.entities.Cineplex;
import main.com.entities.Seating;
import main.com.repositories.CineplexRepository;

public class CineplexManagementService {
    private CineplexRepository _repo = CineplexRepository.getInstance();
    public Cineplex addCineplex(String name){
        return _repo.createCineplex(name);
    }
    public boolean RemoveCineplex(String name){
        return _repo.removeCineplex(name);
    }
    public Cinema AddCinema(Cineplex cineplex, String name, CinemaType type, Seating s){
        return cineplex.addCinema(name, type, s);
    }
    public boolean RemoveCinema(Cineplex cineplex, String name){
        return cineplex.removeCinema(name);
    }
}
