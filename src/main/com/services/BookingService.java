package main.com.services;

public class BookingService{

    private CustomerRepository _repo = CustomerRepository.getInstance();

     public boolean GetCustomerByPhone(int phone){
         return _repo.GetCustomer(phone);
     }
     public boolean CreateCustomer(String name, String email, int phone){
         return _repo.CreateCustomer(name, email, phone);
     }
 }