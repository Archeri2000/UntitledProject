package main.com.services;

import main.com.entities.*;
import main.com.repositories.MovieRepository;
import main.com.repositories.PriceRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class PriceManagementService {
    private PriceRepository _priceRepo = PriceRepository.getInstance();

    public PriceManagementService(){
    }
    public boolean SetCinemaTypeMultiplier(CinemaType type, double multiplier){
        return _priceRepo.setCinemaMultipliers(type, multiplier);
    }
    public boolean SetMovieTypePrice(ShowingEnum type, double price){
        return _priceRepo.setMoviePrice(type, price);
    }
    public boolean SetAgeGroupMultiplier(AgeGroup type, double multiplier){
        return _priceRepo.setAgeMultipliers(type, multiplier);
    }
    public boolean SetDayTypeMultiplier(DayType type, double multiplier){
        return _priceRepo.setDayMultipliers(type, multiplier);
    }
    public boolean AddPublicHoliday(LocalDateTime day){
        //TODO
        return false;
    }
    public HashMap<CinemaType, Double> GetCinemaTypeMultiplier(){
        return _priceRepo.CinemaTypeMultipliers;
    }
    public HashMap<ShowingEnum, Double> GetMovieTypePrice(){
        return _priceRepo.MoviePrices;
    }
    public HashMap<AgeGroup, Double> GetAgeGroupMultiplier(){
        return _priceRepo.AgeMultipliers;
    }
    public HashMap<DayType, Double> GetDayTypeMultiplier(){
        return _priceRepo.DayMultiplier;
    }

}
