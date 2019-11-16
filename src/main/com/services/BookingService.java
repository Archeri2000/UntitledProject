package main.com.services;

import main.com.entities.AgeGroup;
import main.com.entities.Customer;
import main.com.entities.Seating;
import main.com.repositories.CustomerRepository;

public class BookingService{

    private CustomerRepository _repo = CustomerRepository.getInstance();

     public Customer getCustomerByPhone(int phone){
         return _repo.getCustomer(phone);
     }
     public Customer createCustomer(String name, String email, int phone){
         return _repo.createCustomer(name, email, phone);
     }
     public Seating getSeating(int[][] seatMap) {

         int row, column;

         System.out.println("Seats are marked by their ticket price.");
         System.out.println("Seats marked with a 0 are unavailable.\n");

         System.out.println("   01 02 03 04 05 06 07 08 09 10");//print column numbers
         System.out.println("   -----------------------------");//print column numbers

         for (row = 0; row < 8; row++) {//outer print loop
             System.out.print(String.format("%02d", row + 1));
             System.out.print("|");//print row numbers

             for (column = 0; column < 10; column++) {//inner print loop
                 System.out.print(String.format("%02d", seatMap[row][column]));
                 System.out.print(" ");

             }

             System.out.println();

         }
        return null;
     }

     public boolean selectedSeats(String seat, AgeGroup group){
         return true;
     }
 }