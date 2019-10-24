package main.com.moviebooking;

import main.com.serialisation.ISerialisable;
import main.com.serialisation.SerialisationUtils;

import java.time.LocalDateTime;
import java.util.*;

public class CinemaSchedule implements ISerialisable {
    private Seating cinemaSeatingPlan;
    private List<MovieTimeslot> movieSchedule = new ArrayList<>();

    public CinemaSchedule(){}

    public CinemaSchedule(Seating cinemaSeatingPlan) {
        this.cinemaSeatingPlan = cinemaSeatingPlan;
    }

    private CinemaSchedule(Seating cinemaSeatingPlan, List<MovieTimeslot> schedules) {
        this.cinemaSeatingPlan = cinemaSeatingPlan;
        this.movieSchedule = schedules;
    }

    public boolean addMovieShowing(Movie movie, LocalDateTime screening_time, int duration_min){
        if(isTimeslotAvailable(screening_time, duration_min)){
            movieSchedule.add(new MovieTimeslot(movie, screening_time, duration_min, cinemaSeatingPlan.getNewSeatingPlan()));
            return true;
        }
        return false;
    }

    private boolean isTimeslotAvailable(LocalDateTime start_time, int duration_min){
        for(MovieTimeslot m: movieSchedule) {
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
                SerialisationUtils.serialiseList(movieSchedule, "timeslots"));
    }

    @Override
    public CinemaSchedule fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        HashMap<String, String> pairs = SerialisationUtils.deserialise(s);
        try{
            assert pairs != null;
            Seating seat = SerialisationUtils.deserialiseObject(Seating.class, pairs.get("seating"));
            List<MovieTimeslot> schedule = SerialisationUtils.deserialiseList(MovieTimeslot.class, pairs.get("timeslots"));
            return new CinemaSchedule(seat, schedule);
        }catch(Exception e){
            throw new InvalidPropertiesFormatException("");
        }
    }
}
