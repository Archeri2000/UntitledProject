package main.com.entities;

import java.util.List;

public class Customer {

    private String name;
    private int mobileNumber;
    private String email;

    public List<Booking> bookings;

    public Customer(){}

    public Customer(String name, int mobileNumber, String email){
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMobileNumber(int number) {
        this.mobileNumber = number;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public List <Booking> getBooking(){return bookings; }

}
