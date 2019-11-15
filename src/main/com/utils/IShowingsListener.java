package main.com.utils;

import main.com.entities.MovieShowing;

public interface IShowingsListener {
    void OnShowingCreateEvent(MovieShowing showing, Object caller);
    void OnShowingDestroyEvent(MovieShowing showing, Object caller);
}
