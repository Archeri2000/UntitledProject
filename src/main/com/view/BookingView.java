package main.com.view;

import main.com.entities.*;
import main.com.repositories.MovieRepository;
import main.com.repositories.PriceRepository;
import main.com.services.BookingService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 
 * Customer use this class make booking
 * @author SS1 Group 6
 * @version 13
 */
public class BookingView {

	/** 
     * Create a booking service object
     */
    private BookingService _serv = new BookingService();

	/** 
     * Method that will display seats of a particular movie and showtime
     * @param showtime		showtime of a movie
     * @param movie	 used to store the movie object
     */
    public void displaySeats(MovieShowing showtime, Movie movie) {

        Seating seating = _serv.getSeating(showtime);
        String seat = seating.getDisplayString();
        String[] print = seat.split("\n");
        for (String value: print){
            System.out.println(value);
        }
    }

	/** 
     * For customer to select seats for a particular movie and showtime
     * @param showtime		showtime of a movie
     * @param movie			used to store the details of a movie
     * @return list of seats that customer wish to select
     */
    public List<Ticket> selectSeats(MovieShowing showtime, Movie movie) {
        System.out.print("How many ticket you want to book? ");
        Scanner sc = new Scanner(System.in);
        int amount = sc.nextInt();
        List<Ticket> tickets= new ArrayList<>();
        double totalPrice = 0;
        for (int i = 0; i < amount; i++) {
            sc.nextLine();
            System.out.print("Which seat are your choice? ");
            String seat = sc.nextLine();
            System.out.print("How old are you? ");
            int age = sc.nextInt();
            AgeGroup ageGroup = AgeGroup.getGroup(age);
            PriceRepository priceRepo = new PriceRepository();
            double price = priceRepo.getPrice(age, showtime);
            if (_serv.selectSeats(seat, showtime)) {
                tickets.add(new Ticket(new Seat(seat, true), ageGroup, price));
            }
            totalPrice += price;
            System.out.println("Total payable amount is: " + totalPrice);
        }
        return tickets;
    }
    
	/** 
     * To get customer
     * @return customer object based on the details
     */
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


	/** 
     * Create a booking for customer
     *@param customer- stores the details of the customer in the customer object
     *@param tickets- stores the list of tickets
     *@param showing - object of type MovieShowing
     *@param cineplex- stores the name of the cineplex
     *@param cinema - used to store the name of the cinema
     */
    public void checkout(Customer customer,List<Ticket> tickets, MovieShowing showing, String cineplex, String cinema) {
        LocalDateTime time = showing.getShowing_time();
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyyMMddhhmm");
        String endTID = sdf.format(time);
        String TID = showing.getShownMovieTitle().substring(0,3) + endTID;
        Booking booking = new Booking(customer, tickets, TID, showing.getShownMovieTitle(), cineplex, cineplex);
        MovieRepository.getInstance().addSales(showing.getShownMovie(), tickets.size());
        customer.addBooking(booking);

    }
}
