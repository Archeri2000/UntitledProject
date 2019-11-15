package main.com.services;

import main.com.entities.Movie;
import main.com.entities.MovieShowing;
import main.com.repositories.MovieRepository;

import java.util.List;

public class MovieQueryService{
    private MovieRepository _repo = MovieRepository.getInstance();

    public List<Movie> getMovie(String Movie){
       return _repo.getMovie(Movie);
    }

    public List<Movie>getTopRatedMovie(int number){
        return _repo.getMovieByRating(number);
    }

    public List<Movie> getPopularMovies(int number){
        return _repo.getMovieByPopularity(number);
    }
    public boolean addReviews(Movie movie, String review, int rating){
        //Review repo not done yet
         return false;
    }
    public List<MovieShowing> getShowtimeTImeslots(Movie movie){
        return _repo.getShowtimes(movie);
    }
}