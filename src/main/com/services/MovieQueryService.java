package main.com.services;

import main.com.entities.Movie;
import main.com.repositories.MovieRepository;

import java.util.List;

public class MovieQueryService{
    private MovieRepository _repo = MovieRepository.getInstance();

    public List<Movie> GetMovie(String Movie){
       return _repo.getMovie(Movie);
    }

    public List<Movie>GetTopRatedMovie(int number){
        return _repo.getMovieByRating(number);
    }

    public List<Movie> GetPopularMovies(int number){
        return _repo.getMovieByPopularity(number);
    }
     public boolean AddReviews(Movie movie, String review, int rating){
        //Review repo not done yet
         return false;
     }
}