package main.com.services;

import main.com.entities.Movie;
import main.com.repositories.MovieRepository;
import main.com.entities.MovieShowing;

import java.util.List;

public class ScheduleService {
    private MovieRepository _repo = MovieRepository.getInstance();
    private MovieShowing currentTimeslot;
    public ScheduleService(){

    }

    public List<MovieShowing> getShowtimeTImeslots(Movie movie){
        return _repo.getShowtimes(movie);
    }
}
