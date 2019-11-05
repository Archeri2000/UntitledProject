package main.com.services;

import main.com.entities.Movie;
import main.com.repositories.MovieRepository;
import main.com.entities.MovieTimeslot;

import java.util.List;

public class ScheduleService {
    private MovieRepository _repo = MovieRepository.getInstance();
    private MovieTimeslot currentTimeslot;
    public ScheduleService(){

    }

    public List<MovieTimeslot> getShowtimeTImeslots(Movie movie){
        return _repo.getShowtimes(movie);
    }
}
