package main.com.services;

import main.com.entities.*;
import main.com.repositories.MovieRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ShowingManagementService {
    private MovieRepository _movieRepo = MovieRepository.getInstance();

    public ShowingManagementService(){
    }

    public MovieShowing addShowing(Cinema cinema, Movie movie, LocalDateTime startTime, ShowingEnum showtype){
        return cinema.addMovieShowing(movie, startTime, showtype);
    }
    public boolean removeShowing(Cinema cinema, MovieShowing showing){
        return cinema.removeMovieShowing(showing);
    }
}
