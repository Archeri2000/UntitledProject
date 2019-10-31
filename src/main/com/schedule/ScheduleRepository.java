package main.com.schedule;

import main.com.moviebooking.Movie;

import java.util.HashMap;
import java.util.List;

public class ScheduleRepository implements IScheduleListener {
    private static ScheduleRepository _static_manager = new ScheduleRepository();
    private HashMap<Movie, List<MovieTimeslot>> MovieShowings = new HashMap<>();
    private ScheduleRepository(){
    }
    public static ScheduleRepository getInstance(){
        if(_static_manager == null){
            _static_manager = new ScheduleRepository();
        }
        return _static_manager;
    }

    public List<MovieTimeslot> getMovieShowings(Movie movie){
        return MovieShowings.get(movie);
    }

    @Override
    public void OnScheduleCreateEvent(MovieTimeslot timeslot) {
        MovieShowings.get(timeslot.getShownMovie()).add(timeslot);
    }

    @Override
    public void OnScheduleDestroyEvent(MovieTimeslot timeslot) {
        MovieShowings.get(timeslot.getShownMovie()).remove(timeslot);
    }
}
