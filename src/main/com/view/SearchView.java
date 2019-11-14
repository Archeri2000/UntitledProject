package main.com.view;

import main.com.entities.Cineplex;
import main.com.entities.Movie;
import main.com.entities.MovieShowing;
import main.com.services.MovieQueryService;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;


public class SearchView {
    Scanner sc = new Scanner(System.in);

    MovieQueryService MQS = new MovieQueryService();

    public void OptionsMenu(){
        System.out.println(" 1. Search by movie");
        System.out.println(" 2. Search by Rating");
        System.out.println(" 3. Search by Ticket Sales");
        System.out.println(" 4. View movie details");
        System.out.println(" 5. Write Review");
        System.out.println(" 6. Get showtimes");
        System.out.println(" 7. Search by Cineplex");
        System.out.println("");

    }
    public Movie MovieSearch() {
        System.out.println("Enter movie name ");
        String movie = sc.next();
        List<Movie> movieList = MQS.GetMovie(movie);


        for (Movie value : movieList) {
            System.out.println(value);
        }
        System.out.print(" Enter exact name ");
        movie = sc.next();
        for (Movie value : movieList) {
            if (movie.equals(value))
                return value;
        }
        System.out.println(" Movie not found!");
        return null;
    }

    public List <Movie> RatingSearch() {
        System.out.println(" Enter The number of Top Rated Movie ");
        int number = sc.nextInt();
        return MQS.GetTopRatedMovie(number);
    }
    public List<Movie> TicketSaleSearch() {
        System.out.println(" Enter the number of Popular Movie");
        int number = sc.nextInt();
        return MQS.GetPopularMovies(number);
    }
    public void WriteReview(Movie movie){
        System.out.println(" Enter review ");
        String review = sc.next();
        System.out.println(" Enter rating (1-5) ");
        int rating = sc.nextInt();
        MQS.AddReviews(movie, review, rating);
    }
    public List <MovieShowing> ViewShowtimes(Movie movie) {
        return MQS.getShowtimeTImeslots(movie);
    }

}
