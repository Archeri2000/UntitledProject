package main.com.services;

import main.com.entities.Movie;
import main.com.entities.RatingEnum;
import main.com.entities.StatusEnum;
import main.com.repositories.MovieRepository;

import java.util.List;

public class MovieManagementService {
    private MovieRepository _movieRepo = MovieRepository.getInstance();

    public MovieManagementService(){
    }

    public Movie addMovie(String title, int duration, RatingEnum rating, StatusEnum status, String synopsis, String director, List<String> cast){
        return _movieRepo.addMovie(title, duration, rating, status, synopsis, director, cast);
    }

    public boolean setMovieStatus(Movie movie, StatusEnum status){
        if(movie == null) return false;
        movie.movieStatus = status;
        return true;
    }

    public boolean setMovieRating(Movie movie, RatingEnum rating){
        if(movie == null) return false;
        movie.rating = rating;
        return true;
    }

    public boolean removeMovie(Movie movie){
        return _movieRepo.removeMovie(movie);
    }

    public List<Movie> getMovie(String name){
        return _movieRepo.getMovie(name);
    }

    public boolean setMovieSynopsis(Movie movie, String synopsis){
        if(movie == null) return false;
        movie.movie_synopsis = synopsis;
        return true;
    }

    public boolean setMovieDirector(Movie movie, String director){
        if(movie == null) return false;
        movie.movie_director = director;
        return true;
    }

    public boolean addMovieCast(Movie movie, String cast){
        if(movie == null) return false;
        if(movie.cast.contains(cast)) return true;
        movie.cast.add(cast);
        return true;
    }

    public boolean removeMovieCast(Movie movie, String cast){
        if(movie == null) return false;
        if(!movie.cast.contains(cast)) return false;
        movie.cast.remove(cast);
        return true;
    }
    public boolean removeReview(Movie movie, int index){
        if (movie.removeReview(index)) return true;
        return false;
    }
}
