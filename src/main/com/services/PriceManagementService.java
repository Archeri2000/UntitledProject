package main.com.services;

import main.com.entities.CinemaSchedule;
import main.com.entities.Movie;
import main.com.entities.MovieShowing;
import main.com.repositories.MovieRepository;
import main.com.repositories.PriceRepository;

import java.time.LocalDateTime;

public class PriceManagementService {
    private PriceManagementService _priceRepo = PriceRepository.getInstance();

    public PriceManagementService(){
    }

    public MovieShowing addShowing(CinemaSchedule cinema, Movie movie, LocalDateTime startTime){
        return cinema.addMovieShowing(movie, startTime, movie.durationMin);
    }
    public boolean removeShowing(CinemaSchedule cinema, MovieShowing showing){
        return cinema.removeMovieShowing(showing);
    }
}
