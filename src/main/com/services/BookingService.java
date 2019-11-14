package main.com.services;

import main.com.entities.AgeGroup;
import main.com.entities.Customer;
import main.com.entities.Seating;
import main.com.repositories.CustomerRepository;

public class BookingService{

    private CustomerRepository _repo = CustomerRepository.getInstance();

     public Customer GetCustomerByPhone(int phone){
         return _repo.getCustomer(phone);
     }
     public boolean CreateCustomer(String name, String email, int phone){
         return _repo.createCustomer(name, email, phone);
     }
     public Seating getSeatinf(){
         return null;
     }
     public boolean selectedSeats(String seat, AgeGroup group){
         return true;
     }
 }