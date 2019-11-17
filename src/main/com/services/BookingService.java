package main.com.services;

import main.com.entities.*;

import main.com.repositories.CustomerRepository;


import java.util.ArrayList;
import java.util.List;

public class BookingService{

    private CustomerRepository _repo = CustomerRepository.getInstance();

     public Customer getCustomerByPhone(int phone){
         return _repo.getCustomer(phone);
     }
     public Customer createCustomer(String name, String email, int phone){
         return _repo.createCustomer(name, email, phone);
     }
     public Seating getSeating(MovieShowing showing) {
         return showing.getSeatingplan();
     }

     public boolean selectSeats(String seat, MovieShowing showing){
         Seating seatplan = showing.getSeatingplan();

         ArrayList<String> selectedSeats = new ArrayList<>();
         if (seatplan.isSeatEmpty(seat))
             selectedSeats.add(seat);

         if (seatplan.setSeatsOccupied(selectedSeats)) return true;
         return false;
     }
 }