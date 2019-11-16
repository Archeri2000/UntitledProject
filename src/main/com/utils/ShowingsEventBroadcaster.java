package main.com.utils;

import main.com.entities.MovieShowing;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ShowingsEventBroadcaster {
    public static List<IShowingsListener> _listeningClasses = new ArrayList<>();
    public static void CreateShowingEvent(MovieShowing showing, Object caller){
        for (IShowingsListener listener: _listeningClasses) {
            if(listener != null) listener.OnShowingCreateEvent(showing, caller);
        }
    }
    public static void DestroyShowingEvent(MovieShowing showing, Object caller){
        for (IShowingsListener listener: _listeningClasses) {
            if(listener != null) listener.OnShowingDestroyEvent(showing, caller);
        }
    }
    public static void AddListener(IShowingsListener listener){
        if(!_listeningClasses.contains(listener)){
            _listeningClasses.add(listener);
        }
    }

    public static void RemoveListener(IShowingsListener listener){
        _listeningClasses.remove(listener);
    }
}
