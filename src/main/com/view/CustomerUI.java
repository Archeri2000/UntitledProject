package main.com.view;

import main.com.entities.*;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the customer interface that will allow them to implement certain functions such as
 * search movie, make booking and view past bookings
 * @author SS1 Group 6
 * @version 13
 */
public class CustomerUI {

	/** 
     * Create object for the respective view class
     */
    BookingView bookv = new BookingView();
    SearchView searchv = new SearchView();
    HistoryView histv = new HistoryView();
    Scanner sc = new Scanner(System.in);

    int choice = 0;

	/** 
     * The menu page of the customer UI before they indicate which function they wish to implement
     */
    public void hi() {
        do {
            System.out.println("1. Search Movie");
            System.out.println("2. Book movie");
            System.out.println("3. Get past booking");
            System.out.println("-1. Quit");

            System.out.print(" Enter Choice: ");
            choice = sc.nextInt();
            sc.nextLine();
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
                                if (movie != null) {
                                    searchv.writeReview(movie);
                                }
                                break;
                            case 5:
                                movie = searchv.movieSearch();
                                List<MovieShowing> showings =searchv.viewShowtimes(movie);
                                if(movie!=null) {
                                    if (!showings.isEmpty()) {
                                        System.out.println("Showing: ");
                                        for (MovieShowing value : showings) {
                                            System.out.println(value.getShowing_time());
                                        }
                                    }else
                                        System.out.println("No Showing found");
                                }
                                break;
                            case -1:
                                decision = true;
                                break;
                            default:
                                System.out.println("Try again ");
                                CustomerUI restart = new CustomerUI();
                                restart.hi();
                                break;
                        }
                    } while (!decision);
                    break;

                //TODO case2 bookings
                case 2:
                    Movie movie = searchv.movieSearch();
                    if (movie!=null) {
                        List<MovieShowing> showings = searchv.viewShowtimes(movie);
                        if (showings != null || !showings.isEmpty()) {
                            System.out.println("Showing: ");
                            for (MovieShowing value : showings) {
                                System.out.println(value.getShowing_time());
                            }
                            System.out.println("Enter Date and Time in format: YYYY-MM-DD HH:MM");
                            String str = sc.nextLine();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDateTime time = LocalDateTime.parse(str, formatter);
                            for (MovieShowing value : showings) {
                                if (value.getShowing_time().equals(time)) {
                                    bookv.displaySeats(value, movie);
                                    List<Seat> seat = bookv.selectSeats(value, movie);
                                    Customer cust = bookv.getCustomerDetails();
                                    bookv.checkout(cust, seat, value);
                                }

                                break;
                            }
                        } else
                            System.out.println(" Showing not found!");
                    }
                    break;
                case 3:
                    List<Booking> bookingList = histv.getPastBooking();
                    try {
                        for (Booking value : bookingList) {
                            System.out.println(value);
                        }
                    } catch (NullPointerException e) {
                        System.out.println("User not found");
                        System.out.println();
                    }
                    break;
                case -1:
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        } while (choice != 4 && choice != -1);
        Main.serialiseManagers();
    }
}