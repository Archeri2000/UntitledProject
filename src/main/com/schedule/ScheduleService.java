package main.com.schedule;

import main.com.moviebooking.Movie;
import main.com.serialisation.SerialisationDependencyManager;

import java.util.List;

public class ScheduleService {
    private ScheduleRepository _repo = ScheduleRepository.getInstance();
    private MovieTimeslot currentTimeslot;
    public ScheduleService(){

    }

    public List<MovieTimeslot> getShowtimeTImeslots(Movie movie){
        return _repo.getMovieShowings(movie);
    }
}
