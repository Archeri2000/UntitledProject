package main.com.serialisation;

import main.com.moviebooking.Movie;

public class SerialisationDependencyManager {
    private static SerialisationDependencyManager _static_manager = new SerialisationDependencyManager();
    private SerialisationDependencyManager(){
    }
    public static SerialisationDependencyManager getInstance(){
        if(_static_manager == null){
            _static_manager = new SerialisationDependencyManager();
        }
        return _static_manager;
    }
    public Movie getMovieByID(String id){
        return null;
    }
}
