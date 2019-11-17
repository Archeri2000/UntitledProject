package main.com.services;

import main.com.entities.*;

import main.com.repositories.CustomerRepository;

import java.util.ArrayList;

/** 
 * This class helps a customer to make a booking 
 * @author SS1 Group 6
 * @version 13
 */

public class BookingService{
	
	 /** 
     * Create a customer repository
     */

	 /** 
     * Create a customer repository
     */

    private CustomerRepository _repo = CustomerRepository.getInstance();


    /** 
     * Get the customer object
     * @param phone 	mobile number of customer
     * @return customer object 
     */
     public Customer getCustomerByPhone(int phone){
         return _repo.getCustomer(phone);
     }
     
     /** 
      * Create and get the customer object
      * @param name		name of customer
      * @param email	email of customer
      * @param phone 	mobile number of customer
      * @return customer object 
      */
     public Customer createCustomer(String name, String email, int phone){
         return _repo.createCustomer(name, email, phone);
     }
     

     /** 
      * Get seating plan for the movie showing
      * @param showing	the movie showing
      * @return the seating plan of the movie showing
      */
     public Seating getSeating(MovieShowing showing) {
         return showing.getSeatingplan();
     }

     /** 
      * To determine if seats are occupied and to select seats
      * @param seat			the seat the customer wish to select
      * @param showing		the movie showing
      * @return True/False	if the seat is successfully selected
      */
     public boolean selectSeats(String seat, MovieShowing showing){
         Seating seatplan = showing.getSeatingplan();

         ArrayList<String> selectedSeats = new ArrayList<>();
         if (seatplan.isSeatEmpty(seat))
             selectedSeats.add(seat);
         else return false;

         return seatplan.setSeatsOccupied(selectedSeats);
     }
 }
