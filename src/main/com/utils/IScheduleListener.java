package main.com.utils;

import main.com.entities.MovieTimeslot;

public interface IScheduleListener {
    void OnScheduleCreateEvent(MovieTimeslot timeslot);
    void OnScheduleDestroyEvent(MovieTimeslot timeslot);
}
