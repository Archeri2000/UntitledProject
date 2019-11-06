package main.com.services;

public class HistoryQueryService {

    private CustomerRepository _repo = CustomerRepository.getInstance();

    public List<Booking> GetHistoryBookings(int phone){
        Customer customer = _repo.getCustomer(phone);
        return customer.GetBookings; //How to get the bookings
    }

    public Booking GetMostRecentBooking(int phone){
        Customer customer = _repo.getCustomer(phone);
        return null;  //How to get booking
    }



}