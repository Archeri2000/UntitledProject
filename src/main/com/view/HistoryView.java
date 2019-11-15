package main.com.view;

import main.com.entities.Booking;
import main.com.services.HistoryQueryService;

import java.util.List;
import java.util.Scanner;

public class HistoryView {

    HistoryQueryService HQS = new HistoryQueryService();

    public List<Booking> getPastBooking() {
        int userMobile;
        System.out.println(" Enter User mobile number");

        Scanner sc = new Scanner(System.in);
        userMobile = sc.nextInt();

		return HQS.getHistoryBookings(userMobile); // need code to retrieve booking history
    }
}
