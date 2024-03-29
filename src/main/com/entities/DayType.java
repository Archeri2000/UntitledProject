package main.com.entities;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

/** This class holds the values of day type
 * @author SS1 Group 6
 * @version 13
 */
public enum DayType {
    Weekday, Saturday, Sunday_PH;

    public static DayType getDayType(LocalDateTime dateTime){
        if(dateTime.getDayOfWeek() == DayOfWeek.SUNDAY){
            return Sunday_PH;
        }else if(dateTime.getDayOfWeek() == DayOfWeek.SATURDAY){
            return Saturday;
        }else{
            return Weekday;
        }
    }
}
