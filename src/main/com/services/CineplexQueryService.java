package main.com.services;

import main.com.entities.Cinema;
import main.com.entities.Cineplex;
import main.com.entities.MovieShowing;
import main.com.repositories.CineplexRepository;

import java.util.List;

public class CineplexQueryService{
    private CineplexRepository _repo = CineplexRepository.getInstance();

    public Cineplex GetCineplex(String name){
        return _repo.GetCineplex(name);
    }
    public List<Cineplex> GetCineplexes(){
        return _repo.GetCineplexs();
    }
    public Cinema GetCinema(Cineplex cineplex, String CinemaID){
        return cineplex.getCinema(CinemaID);
    }
    public List<Cinema> GetCinemas(Cineplex Cineplex){
        return Cineplex.getCinemas();
    }

    public List<MovieShowing> GetShows(Cinema cinema){
        return cinema.getShows();
    }
}