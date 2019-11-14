package main.com.services;

import main.com.entities.Booking;
import main.com.entities.Customer;
import main.com.repositories.CustomerRepository;

import java.util.List;

public class HistoryQueryService {

    private CustomerRepository _repo = CustomerRepository.getInstance();

    public List<Booking> GetHistoryBookings(int phone){
        Customer customer = _repo.getCustomer(phone);
        return customer.getBooking();
    }
}