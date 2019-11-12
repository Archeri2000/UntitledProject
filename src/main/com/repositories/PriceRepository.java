package main.com.repositories;

import main.com.entities.*;
import main.com.utils.ISerialisable;

import java.util.*;
public class PriceRepository implements ISerialisable {
	public HashMap<AgeGroup, Float> AgeMultipliers = new HashMap<>();
	public HashMap<CinemaType, Float> CinemaTypeMultipliers = new HashMap<>() ;
	public HashMap<StatusEnum,Float> MoviePrices = new HashMap<>() ;
	public HashMap<DayType, Float> DayMultiplier = new HashMap<>();
	
	
	public String toSerialisedString()
	{
		return "";
	}
	public PriceRepository fromSerialisedString(String s)
	{
		return null;
	}
	
	private boolean SetAgeMultipliers(AgeGroup age, float multiplier)
	{
		if(AgeMultipliers.containsKey(age))
			return false;
		AgeMultipliers.put(age,multiplier);
		return true;
	}
	private boolean setCinemaMultipliers(CinemaType cinema, float multiplier)
	{
		if(CinemaTypeMultipliers.containsKey(cinema))
			return false;
		CinemaTypeMultipliers.put(cinema,multiplier);
		return true;
	}
	private boolean setMoviePrice(StatusEnum showing, float price)
	{
		if(MoviePrices.containsKey(showing))
			return false;
		MoviePrices.put(showing,price);
		return true;
	}
	private boolean setDayMultipliers(DayType day, float multiplier)
	{
		if(DayMultiplier.containsKey(day))
			return false;
		DayMultiplier.put(day,multiplier);
		return true;
	}
	private float GetPrice(AgeGroup age, CinemaType cinema, StatusEnum showing, DayType day)
	{
		float price;
		float age_price = AgeMultipliers.get(age);
		float cinema_price = CinemaTypeMultipliers.get(cinema);
		float showing_price = MoviePrices.get(showing);
		float day_price = DayMultiplier.get(day);
		price = age_price * cinema_price * showing_price * day_price;
		return price;
	}
	
}
