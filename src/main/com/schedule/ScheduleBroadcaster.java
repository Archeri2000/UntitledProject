package main.com.schedule;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ScheduleBroadcaster {
    public static List<WeakReference<IScheduleListener>> _listeningClasses = new ArrayList<>();
    public static void CreateEvent(MovieTimeslot timeslot){
        for (WeakReference<IScheduleListener> listenerRef: _listeningClasses) {
            IScheduleListener listener = listenerRef.get();
            if(listener != null) listener.OnScheduleCreateEvent(timeslot);
        }
    }
    public static void DestroyEvent(MovieTimeslot timeslot){
        for (WeakReference<IScheduleListener> listenerRef: _listeningClasses) {
            IScheduleListener listener = listenerRef.get();
            if(listener != null) listener.OnScheduleDestroyEvent(timeslot);
        }
    }
    public static void AddListener(IScheduleListener listener){
        _listeningClasses.add(new WeakReference<IScheduleListener>(listener));
    }

    public static void RemoveListener(IScheduleListener listener){
        for(int i = 0; i < _listeningClasses.size(); i++){
            if(_listeningClasses.get(i).get() == listener){
                _listeningClasses.remove(i);
                return;
            }
        }
    }
}
