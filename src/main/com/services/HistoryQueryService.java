package main.com.services;

import main.com.entities.Booking;
import main.com.entities.Customer;
import main.com.repositories.CustomerRepository;

import java.util.List;

public class HistoryQueryService {

    private CustomerRepository _repo = CustomerRepository.getInstance();

    public List<Booking> getHistoryBookings(int phone) {
        try {

            Customer customer = _repo.getCustomer(phone);
            return customer.getBookings();
        } catch (NullPointerException e) {
            System.out.println("");
            return null;
        }
    }
}