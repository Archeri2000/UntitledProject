package main.com.services;

import main.com.entities.*;
import main.com.repositories.MovieRepository;
import main.com.repositories.PriceRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/** 
 * Admin staff uses this class to manage pricing of tickets
 * @author SS1 Group 6
 * @version 13
 */

public class PriceManagementService {
	
	/** 
     * Create a price repository
     */
    private PriceRepository _priceRepo = PriceRepository.getInstance();

    /** 
     * PriceMangementService constructor
     */
    public PriceManagementService(){
    }
    
    /** 
     
     * To set the type of cinema
     *@param type- object of CinemaType
     *@param multiplier - multiplier value for the cinematype
     * @return True/False if the cinema type is successfully set
     */
    public boolean SetCinemaTypeMultiplier(CinemaType type, double multiplier){
        return _priceRepo.setCinemaMultipliers(type, multiplier);
    }
    
    /** 
     * To set the type of movie
     *@param type- showing type of the movie
     *@param price- price for the showing type
     * @return True/False if the movie type is successfully set
     */
    public boolean SetMovieTypePrice(ShowingEnum type, double price){
        return _priceRepo.setMoviePrice(type, price);
    }
    
    /** 
     * To set the type of age group
     *@param type- object of AgeGroup
     *@param multiplier- multiplier value for the agegroup
     * @return True/False if the age group is successfully set
     */
    public boolean SetAgeGroupMultiplier(AgeGroup type, double multiplier){
        return _priceRepo.setAgeMultipliers(type, multiplier);
    }
    
    /** 
     * To set the day type
     *@param type-day type(weekend/weekday/holiday)
     *@param multiplier - multiplier value for the day-type
     * @return True/False if the dat type is successfully set
     */
    public boolean SetDayTypeMultiplier(DayType type, double multiplier){
        return _priceRepo.setDayMultipliers(type, multiplier);
    }
    
    /** 
     * To add public holiday
     *@param day- date of the public holiday
     * @return True/False if the public holiday is successfully added
     */
    public boolean AddPublicHoliday(LocalDate day){
        return _priceRepo.addPublicHoliday(day);
    }
    
    /** 
     * To remove public holiday
     *@param day- date of public holiday
     * @return True/False if the public holiday is successfully removed
     */
    public boolean RemovePublicHoliday(LocalDate day){
        return _priceRepo.removePublicHoliday(day);
    }
    
    /** 
    *To get cinema type multipliers
     * @return the cinema types
     */
    public HashMap<CinemaType, Double> GetCinemaTypeMultiplier(){
        return _priceRepo.CinemaTypeMultipliers;
    }
    
    /** 
     *To get show type multipliers 
     * @return the movie types
     */
    public HashMap<ShowingEnum, Double> GetMovieTypePrice(){
        return _priceRepo.MoviePrices;
    }
    
    /** 
     * to get age type multipliers
     * @return the agegroup types
     */
    public HashMap<AgeGroup, Double> GetAgeGroupMultiplier(){
        return _priceRepo.AgeMultipliers;
    }
    
    /** 
     *  to get day type multipliers
     * @return the day types
     */
    public HashMap<DayType, Double> GetDayTypeMultiplier(){
        return _priceRepo.DayMultiplier;
    }
    
    /** 
     * To get the list of public holidays
     * @return the list of public holidays
     */
    public List<LocalDate> GetPublicHolidays(){ 
    	return _priceRepo.Public_Holidays; 
    }
}
