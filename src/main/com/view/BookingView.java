package main.com.view;

import main.com.entities.*;
import main.com.services.BookingService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BookingView {

    private BookingService _serv = new BookingService();

    public void displaySeats(MovieShowing showtime, Movie movie, Cineplex cineplex, Cinema cinema){

    }
    public void selecrSeats(Seating seating){

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
