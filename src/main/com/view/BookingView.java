package main.com.view;

import main.com.entities.*;
import main.com.services.BookingService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BookingView {

    private BookingService _serv = new BookingService();

    public void DisplaySeats(MovieShowing showtime, Movie movie, Cineplex cineplex, Cinema cinema){

    }
    public void selecrSeats(Seating seating){

    }
    public Customer getCustomerDetails() {
        int userMobile;
        System.out.println(" Enter User mobile number");

        Scanner sc = new Scanner(System.in);
        userMobile = sc.nextInt();

        Customer customer = _serv.GetCustomerByPhone(userMobile);
        if (customer == null) {
            System.out.println("Enter name");
            String name = sc.next();
            System.out.println(" Enter email ");
            String email = sc.next();
            _serv.CreateCustomer(name, email, userMobile);
        }
        // return what?
        return customer;
    }

    public void Checkout(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyyMMddhhmm");
        String endTID = sdf.format(now);

    }
}
