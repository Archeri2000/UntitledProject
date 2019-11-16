package main.com.repositories;

import main.com.entities.*;
import main.com.utils.ISerialisable;

import java.util.*;
public class PriceRepository implements ISerialisable {
	public HashMap<AgeGroup, Float> AgeMultipliers = new HashMap<>();
	public HashMap<CinemaType, Float> CinemaTypeMultipliers = new HashMap<>();
	public HashMap<ShowingEnum,Float> MoviePrices = new HashMap<>();
	public HashMap<DayType, Float> DayMultiplier = new HashMap<>();
	
	public boolean setAgeMultipliers(AgeGroup age, float multiplier)
	{
		if(AgeMultipliers.containsKey(age))
			return false;
		AgeMultipliers.put(age,multiplier);
		return true;
	}
	public boolean setCinemaMultipliers(CinemaType cinema, float multiplier)
	{
		if(CinemaTypeMultipliers.containsKey(cinema))
			return false;
		CinemaTypeMultipliers.put(cinema,multiplier);
		return true;
	}
	public boolean setMoviePrice(ShowingEnum showing, float price)
	{
		if(MoviePrices.containsKey(showing))
			return false;
		MoviePrices.put(showing,price);
		return true;
	}
	public boolean setDayMultipliers(DayType day, float multiplier)
	{
		if(DayMultiplier.containsKey(day))
			return false;
		DayMultiplier.put(day,multiplier);
		return true;
	}
	public float GetPrice(AgeGroup age, CinemaType cinema, ShowingEnum showing, DayType day)
	{
		float price;
		float age_price = AgeMultipliers.get(age);
		float cinema_price = CinemaTypeMultipliers.get(cinema);
		float showing_price = MoviePrices.get(showing);
		float day_price = DayMultiplier.get(day);
		price = age_price * cinema_price * showing_price * day_price;
		return price;
	}

	private static PriceRepository _repo;
	public static PriceRepository getInstance(){
		if(_repo == null){
			_repo = new PriceRepository();
		}
		return _repo;
	}
	public PriceRepository(){}
	//TODO
	@Override
	public String toSerialisedString() {
		return "asdf";
	}

	@Override
	public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
		return getInstance();
	}
}
