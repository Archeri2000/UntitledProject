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
    public void addReviews(Movie movie, String review, double rating){
        movie.addReview(review, rating);
    }
    public List<MovieShowing> getShowtimeTimeslots(Movie movie){
        return _repo.getShowtimes(movie);
    }
}