package main.com.view;

import java.util.Scanner;

public class CustomerUI {

    int choice = 0;

    public void displayMenu() {

        do {
            System.out.println("Welcome to MOBLIMA");
            System.out.println(" 1. Search movie");
            System.out.println(" 2. Book and Purchase Ticket");
            System.out.println(" 3. Display Booking History");
            System.out.println(" ");

            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    OptionsMenu();
                    break;
                case 2: // Book and Purchase Ticket
                    System.out.println(" Enter movie name. ");
                    String movie = sc.next();
                    System.out.println(" Enter showtimes. ");
                    String showtimes = sc.next();
                    System.out.println(" Enter cineplex ");
                    String cineplex = sc.next();
                    System.out.println(" Enter cinema. ");
                    String cinema =  sc.next();
                    DisplaySeats(movie, showtimes, cineplex, cinema);
                    System.out.println("How many seats to book");
                    int numberOfSeats = sc.nextInt();
                    for (int i = 1; i <= numberOfSeats; i++){
                        System.out.println(" Enter seats. ");
                        String seats = sc.next();
                        SelectSeats(seats);
                    }
                    GetCustomerDetails();
                    Checkout();
                    break;
                case 3:
                    //** View Book History
                    getPastBookings();
                    break;
                default:
                    System.out.println("Wrong Input!!");
                    break;

            }
        } while (choice > 3);

    }

    public void OptionsMenu(){
        choice = 0;

        do {
            System.out.println(" 1. Search by movie");
            System.out.println(" 2. Search by Rating");
            System.out.println(" 3. Search by Ticket Sales");
            System.out.println(" 4. View movie details");
            System.out.println(" 5. Write Review");
            System.out.println(" 6. Get showtimes");
            System.out.println(" 7. Search by Cineplex");
            System.out.println("");

            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    MovieSearch();
                    break;
                case 2:
                    RatingSearch();
                    break;
                case 3:
                    TicketSaleSearch();
                    break;
                case 4:
                    System.out.println(" Enter movie name. ");
                    String movie = sc.next();
                    ViewMovieDetails(movie);
                    break;
                case 5:
                    System.out.println(" Enter movie name. ");
                    movie = sc.next();
                    WriteReview(movie);
                    break;
                case 6:
                    System.out.println(" Enter movie name. ");
                    movie = sc.next();
                    ViewShowtimes(movie);
                    break;
                case 7:
                    CineplexSearch();
                    break;
                default:
                    System.out.println("Wrong input!!");
            }
        } while (choice > 7);
    }

    public void BookingView(){





    }
    public void displayMovieListing() {
        choice = 0; // each menu manage their own choice integer

        do {
            System.out.println("Movie Listing");
            System.out.println(" 1. All Movies");
            System.out.println(" 2. Top 5 Movies");
            System.out.println(" 3. Search Movies");
            System.out.println("");

            Scanner sc = new Scanner(System.in);

            choice = sc.nextInt();
            switch (choice) {
                case 1:
//					printHeader("Movies");
//					mMovieMenu.displayMenu(chooseMovie(""));
                    break;
                case 2:
//					mTopRankingMenu.displayMenu();
                    break;
                case 3:
//					searchMovies();
                    break;
                default:
                    System.out.println("Wrong input!!");
            }

        } while (choice > 3);
    }

    public void displayBookingHistory() {
        int userMobile;

        System.out.println(" Enter User mobile number");

        Scanner sc = new Scanner(System.in);
        userMobile = sc.nextInt();

//		displayBookingRecords(userMobile); // need code to retrieve booking history

    }
}