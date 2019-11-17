package main.com.view;

import main.com.entities.Booking;
import main.com.services.HistoryQueryService;

import java.util.List;
import java.util.Scanner;

/** 
 * Customer use this class view past bookings
 * @author SS1 Group 6
 * @version 13
 */
public class HistoryView {

	/** 
     * Create a history query service object
     */
    HistoryQueryService HQS = new HistoryQueryService();

    /** 
     * Method to retrieve past bookings of a customer
     * @return list of bookings made in the past
     */
    public List<Booking> getPastBooking() {
        int userMobile;
        System.out.println(" Enter User mobile number");

        Scanner sc = new Scanner(System.in);
        userMobile = sc.nextInt();
        sc.nextLine();
		return HQS.getHistoryBookings(userMobile);  // need code to retrieve booking history
    }
}