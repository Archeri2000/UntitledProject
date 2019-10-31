package main.com.schedule;

public interface IScheduleListener {
    void OnScheduleCreateEvent(MovieTimeslot timeslot);
    void OnScheduleDestroyEvent(MovieTimeslot timeslot);
}
