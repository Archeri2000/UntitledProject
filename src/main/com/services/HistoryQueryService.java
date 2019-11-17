package main.com.services;

import main.com.entities.Booking;
import main.com.entities.Customer;
import main.com.repositories.CustomerRepository;

import java.util.List;

/** 
 * This class helps a customer to check his past movie bookings
 */
public class HistoryQueryService {

	/** 
     * Create a customer repository
     */
    private CustomerRepository _repo = CustomerRepository.getInstance();

    /** 
     * Get the list of bookings
     * @param phone 	phone number of customer
     * @return list of past bookings made by the customer
     */
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