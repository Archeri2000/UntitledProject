package main.com.services;

import main.com.entities.*;
import main.com.repositories.MovieRepository;
import main.com.repositories.PriceRepository;

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
     * @return True/False if the cinema type is successfully set
     */
    public boolean SetCinemaTypeMultiplier(CinemaType type, double multiplier){
        return _priceRepo.setCinemaMultipliers(type, multiplier);
    }
    
    /** 
     * To set the type of movie
     * @return True/False if the movie type is successfully set
     */
    public boolean SetMovieTypePrice(ShowingEnum type, double price){
        return _priceRepo.setMoviePrice(type, price);
    }
    
    /** 
     * To set the type of age group
     * @return True/False if the age group is successfully set
     */
    public boolean SetAgeGroupMultiplier(AgeGroup type, double multiplier){
        return _priceRepo.setAgeMultipliers(type, multiplier);
    }
    
    /** 
     * To set the day type
     * @return True/False if the dat type is successfully set
     */
    public boolean SetDayTypeMultiplier(DayType type, double multiplier){
        return _priceRepo.setDayMultipliers(type, multiplier);
    }
    
    /** 
     * To add public holiday
     * @return True/False if the public holiday is successfully added
     */
    public boolean AddPublicHoliday(LocalDate day){
        return _priceRepo.addPublicHoliday(day);
    }
    
    /** 
     * To remove public holiday
     * @return True/False if the public holiday is successfully removed
     */
    public boolean RemovePublicHoliday(LocalDate day){
        return _priceRepo.removePublicHoliday(day);
    }
    
    /** 
     * @return the cinema types
     */
    public HashMap<CinemaType, Double> GetCinemaTypeMultiplier(){
        return _priceRepo.CinemaTypeMultipliers;
    }
    
    /** 
     * @return the movie types
     */
    public HashMap<ShowingEnum, Double> GetMovieTypePrice(){
        return _priceRepo.MoviePrices;
    }
    
    /** 
     * @return the agegroup types
     */
    public HashMap<AgeGroup, Double> GetAgeGroupMultiplier(){
        return _priceRepo.AgeMultipliers;
    }
    
    /** 
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
