package main.com.entities;

import main.com.utils.IShowingsListener;
import main.com.utils.ShowingsEventBroadcaster;
import main.com.utils.ISerialisable;
import main.com.utils.SerialisationUtils;

import java.time.LocalDateTime;
import java.util.*;

public class CinemaSchedule implements ISerialisable, IShowingsListener {
    private Seating cinemaSeatingPlan;
    private List<MovieShowing> movieSchedule = new ArrayList<>();

    public CinemaSchedule(){
    }

    public CinemaSchedule(Seating cinemaSeatingPlan) {
        this.cinemaSeatingPlan = cinemaSeatingPlan;
        ShowingsEventBroadcaster.AddListener(this);
    }

    private CinemaSchedule(Seating cinemaSeatingPlan, List<MovieShowing> schedules) {
        this.cinemaSeatingPlan = cinemaSeatingPlan;
        this.movieSchedule = schedules;
    }

    public MovieShowing addMovieShowing(Movie movie, LocalDateTime screening_time, int duration_min){
        if(isTimeslotAvailable(screening_time, duration_min)){
            MovieShowing showing = new MovieShowing(movie, screening_time, duration_min, cinemaSeatingPlan.getNewSeatingPlan());
            movieSchedule.add(showing);
            ShowingsEventBroadcaster.CreateShowingEvent(showing, this);
            return showing;
        }
        return null;
    }

    public boolean removeMovieShowing(MovieShowing showing){
        if(movieSchedule.contains(showing)){
            movieSchedule.remove(showing);
            ShowingsEventBroadcaster.DestroyShowingEvent(showing, this);
            return true;
        }
        return false;
    }

    private boolean isTimeslotAvailable(LocalDateTime start_time, int duration_min){
        for(MovieShowing m: movieSchedule) {
            if(m.getEnding_time().isAfter(start_time)){
                if(m.getEnding_time().isBefore(start_time.plusMinutes(duration_min))) return false;
            }else if(m.getShowing_time().isBefore(start_time.plusMinutes(duration_min))){
                if(m.getShowing_time().isAfter(start_time)) return false;
            }
        }
        return true;
    }

    @Override
    public String toSerialisedString(){
        return SerialisationUtils.serialise(SerialisationUtils.serialiseObject(cinemaSeatingPlan, "seating"),
                SerialisationUtils.serialiseList(movieSchedule, "showings"));
    }

    @Override
    public CinemaSchedule fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        HashMap<String, String> pairs = SerialisationUtils.deserialise(s);
        try{
            assert pairs != null;
            Seating seat = SerialisationUtils.deserialiseObject(Seating.class, pairs.get("seating"));
            List<MovieShowing> schedule = SerialisationUtils.deserialiseList(MovieShowing.class, pairs.get("showings"));
            return new CinemaSchedule(seat, schedule);
        }catch(Exception e){
            throw new InvalidPropertiesFormatException("");
        }
    }

    @Override
    public void OnShowingCreateEvent(MovieShowing showing, Object caller) {
    }

    @Override
    public void OnShowingDestroyEvent(MovieShowing showing, Object caller) {
        if(caller == this) return;
        if(movieSchedule.contains(showing)) movieSchedule.remove(showing);
    }
}
