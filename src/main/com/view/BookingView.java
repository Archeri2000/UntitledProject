package main.com.view;

import main.com.entities.*;
import main.com.services.BookingService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingView {

    private BookingService _serv = new BookingService();

    public void displaySeats(MovieShowing showtime, Movie movie) {

        Seating seating = _serv.getSeating(showtime);
        String seat = seating.getDisplayString();
        String[] print = seat.split("\n");
        for (String value: print){
            System.out.println(value);
        }
    }

    public List<Ticket> selectSeats(MovieShowing showtime, Movie movie) {
        System.out.print("How many ticket you want to book? ");
        Scanner sc = new Scanner(System.in);
        int amount = sc.nextInt();
        List<Ticket> tickets= new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            sc.nextLine();
            System.out.print("Which seat are your choice? ");
            String seat = sc.nextLine();
            System.out.print("How old are you? ");
            int age = sc.nextInt();
            AgeGroup ageGroup = AgeGroup.getGroup(age);
            if (_serv.selectSeats(seat, showtime)) {
                tickets.add(new Ticket(new Seat(seat, false), ageGroup));
            }
        }
        return tickets;
    }
    public Customer getCustomerDetails() {
        int userMobile;
        System.out.print("Enter User mobile number: ");

        Scanner sc = new Scanner(System.in);
        userMobile = sc.nextInt();
        sc.nextLine();

        Customer customer = _serv.getCustomerByPhone(userMobile);
        if (customer == null) {
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter email: ");
            String email = sc.nextLine();
            customer = _serv.createCustomer(name, email, userMobile);
        }
        return customer;
    }

    public void checkout(Customer customer,List<Ticket> tickets, MovieShowing showing, Movie movie, Cineplex cineplex, Cinema cinema) {
        LocalDateTime time = showing.getShowing_time();
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyyMMddhhmm");
        String endTID = sdf.format(time);
        String TID = showing.getShownMovieTitle().substring(0,3) + endTID;

        String cineplexName = cineplex.getCineplexName();
        String CinemaName = cinema.getName();
        Booking booking = new Booking(customer, tickets, TID, movie.movie_title, cineplexName,cineplexName);
    }
}
