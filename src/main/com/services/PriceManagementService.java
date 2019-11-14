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
    public boolean SetCinemaTypeMultiplier(CinemaType type, float multiplier){
        return _priceRepo.setCinemaMultipliers(type, multiplier);
    }
    public boolean SetMovieTypePrice(ShowingEnum type, float price){
        return _priceRepo.setMoviePrice(type, price);
    }
    public boolean SetAgeGroupMultiplier(AgeGroup type, float multiplier){
        return _priceRepo.setAgeMultipliers(type, multiplier);
    }
    public boolean SetDayTypeMultiplier(DayType type, float multiplier){
        return _priceRepo.setDayMultipliers(type, multiplier);
    }
    public boolean AddPublicHoliday(LocalDateTime day){
        //TODO
        return false;
    }
    public HashMap<CinemaType, Float> GetCinemaTypeMultiplier(){
        return _priceRepo.CinemaTypeMultipliers;
    }
    public HashMap<ShowingEnum, Float> GetMovieTypePrice(){
        return _priceRepo.MoviePrices;
    }
    public HashMap<AgeGroup, Float> GetAgeGroupMultiplier(){
        return _priceRepo.AgeMultipliers;
    }
    public HashMap<DayType, Float> GetDayTypeMultiplier(){
        return _priceRepo.DayMultiplier;
    }

}
