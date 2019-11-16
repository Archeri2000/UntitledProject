package main.com.view;

import main.com.entities.Booking;
import main.com.entities.Movie;

import main.com.entities.RatingEnum;
import main.com.entities.StatusEnum;
import main.com.repositories.MovieRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class CustomerUI {

    BookingView bookv = new BookingView();
    SearchView searchv = new SearchView();
    HistoryView histv = new HistoryView();
    Scanner sc = new Scanner(System.in);

    int choice = 0;

    public void hi() {
        do {
            System.out.println("1. Search Movie");
            System.out.println("2. Book movie");
            System.out.println("3. Get past booking");

            System.out.print(" Enter Choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case (1):
                    boolean decision = false;
                    do {

                        searchv.optionsMenu();
                        System.out.print("Enter Option: ");
                        int choice2 = sc.nextInt();
                        sc.nextLine();
                        switch (choice2) {
                            case 1:
                                Movie movie = searchv.movieSearch();
                                searchv.viewMovieDetails(movie);
                                break;
                            case 2:
                                List<Movie> movieByRatings = searchv.ratingSearch();
                                for (Movie value : movieByRatings) {
                                    System.out.println(value.movie_title);
                                }
                                break;
                            case 3:
                                List<Movie> movieByPopularity = searchv.ticketSaleSearch();
                                for (Movie value : movieByPopularity) {
                                    System.out.println(value.movie_title);
                                }
                                break;
                            case 4:
                                movie = searchv.movieSearch();
                                if( movie != null) {
                                    searchv.writeReview(movie);
                                }
                                break;
                            case 5:
                                movie = searchv.movieSearch();
                                searchv.viewShowtimes(movie);
                                break;
                            case 6:
                                decision = true;
                                break;
                            default:
                                System.out.println("Try again ");
                                CustomerUI restart = new CustomerUI();
                                restart.hi();
                                break;
                        }
                    } while (decision == false);
                    break;

                //TODO case2 bookings
                case 3:
                    List<Booking> bookingList = histv.getPastBooking();
                    try {
                        for (Booking value : bookingList) {
                            System.out.println(value);
                        }
                    } catch (NullPointerException e) {
                        System.out.println("User not found");
                    }
            }
        } while (choice > 3);
    }
    public static void main(String[] args) {
        MovieRepository repo = new MovieRepository();
        List<String> messages = Arrays.asList("Hello", "World!", "How", "Are", "You");
        repo.addMovie("joker", 180, RatingEnum.PG, StatusEnum.Showing, "save me plz", "direct1", messages);
        CustomerUI obj = new CustomerUI();
        obj.hi();
    }
}
