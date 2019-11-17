package main.com.view;

import main.com.entities.*;
import main.com.services.BookingService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public void selectSeats(MovieShowing showtime, Movie movie){
        System.out.print("How many ticket you want to book? ");
        Scanner sc = new Scanner(System.in);
        int amount = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < amount; i++){
            System.out.print("Which seat are your choice? ") ;
            String seat = sc.nextLine();
            System.out.print("How old are you? ");
            int age = sc.nextInt();
            _serv.selectSeats(seat, showtime);
        }

    }
    public Customer getCustomerDetails() {
        int userMobile;
        System.out.println(" Enter User mobile number");

        Scanner sc = new Scanner(System.in);
        userMobile = sc.nextInt();

        Customer customer = _serv.getCustomerByPhone(userMobile);
        if (customer == null) {
            System.out.println("Enter name");
            String name = sc.nextLine();
            System.out.println(" Enter email ");
            String email = sc.nextLine();
            customer = _serv.createCustomer(name, email, userMobile);
        }
        return customer;
    }

    public void checkout(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyyMMddhhmm");
        String endTID = sdf.format(now);

    }
}
