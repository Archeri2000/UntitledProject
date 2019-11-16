package main.com.repositories;

import main.com.entities.*;
import main.com.utils.ISerialisable;
import main.com.utils.StringDoublePair;
import main.com.utils.StringIntPair;

import static main.com.utils.SerialisationUtils.*;

import java.util.*;
import java.util.stream.Collectors;

public class PriceRepository implements ISerialisable {
	public HashMap<AgeGroup, Double> AgeMultipliers = new HashMap<>();
	public HashMap<CinemaType, Double> CinemaTypeMultipliers = new HashMap<>();
	public HashMap<ShowingEnum,Double> MoviePrices = new HashMap<>();
	public HashMap<DayType, Double> DayMultiplier = new HashMap<>();
	
	public boolean setAgeMultipliers(AgeGroup age, double multiplier)
	{
		if(AgeMultipliers.containsKey(age))
			return false;
		AgeMultipliers.put(age,multiplier);
		return true;
	}
	public boolean setCinemaMultipliers(CinemaType cinema, double multiplier)
	{
		if(CinemaTypeMultipliers.containsKey(cinema))
			return false;
		CinemaTypeMultipliers.put(cinema,multiplier);
		return true;
	}
	public boolean setMoviePrice(ShowingEnum showing, double price)
	{
		if(MoviePrices.containsKey(showing))
			return false;
		MoviePrices.put(showing,price);
		return true;
	}
	public boolean setDayMultipliers(DayType day, double multiplier)
	{
		if(DayMultiplier.containsKey(day))
			return false;
		DayMultiplier.put(day,multiplier);
		return true;
	}
	public double GetPrice(AgeGroup age, CinemaType cinema, ShowingEnum showing, DayType day)
	{
		double price;
		double age_price = AgeMultipliers.get(age);
		double cinema_price = CinemaTypeMultipliers.get(cinema);
		double showing_price = MoviePrices.get(showing);
		double day_price = DayMultiplier.get(day);
		price = age_price * cinema_price * showing_price * day_price;
		return price;
	}

	private static PriceRepository _static_manager;
	public static PriceRepository getInstance(){
		if(_static_manager == null){
			_static_manager = new PriceRepository();
		}
		return _static_manager;
	}
	public PriceRepository(){}
	//TODO
	@Override
	public String toSerialisedString() {
		List<StringDoublePair> age = AgeMultipliers
				.entrySet()
				.stream()
				.map(x -> new StringDoublePair(x.getKey().name(), (double)x.getValue())).collect(Collectors.toList());
		List<StringDoublePair> cinema = CinemaTypeMultipliers
				.entrySet()
				.stream()
				.map(x -> new StringDoublePair(x.getKey().name(), (double)x.getValue())).collect(Collectors.toList());
		List<StringDoublePair> showing = MoviePrices
				.entrySet()
				.stream()
				.map(x -> new StringDoublePair(x.getKey().name(), (double)x.getValue())).collect(Collectors.toList());
		List<StringDoublePair> day = DayMultiplier
				.entrySet()
				.stream()
				.map(x -> new StringDoublePair(x.getKey().name(), (double)x.getValue())).collect(Collectors.toList());
		return serialise(
				serialiseList(age, "age"),
				serialiseList(cinema, "cinema"),
				serialiseList(showing, "showing"),
				serialiseList(day, "day")
		);
	}

	@Override
	public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
		_static_manager = this;
		HashMap<String, String> pairs = deserialise(s);
		assert pairs != null;
		List<StringDoublePair> age = deserialiseList(StringDoublePair.class, pairs.get("age"));
		for(StringDoublePair pair: age){
			AgeMultipliers.put(AgeGroup.valueOf(pair.First()), pair.Second());
		}
		List<StringDoublePair> cinema = deserialiseList(StringDoublePair.class, pairs.get("cinema"));
		for(StringDoublePair pair: cinema){
			CinemaTypeMultipliers.put(CinemaType.valueOf(pair.First()), pair.Second());
		}
		List<StringDoublePair> showing = deserialiseList(StringDoublePair.class, pairs.get("showing"));
		for(StringDoublePair pair: showing){
			MoviePrices.put(ShowingEnum.valueOf(pair.First()), pair.Second());
		}
		List<StringDoublePair> day = deserialiseList(StringDoublePair.class, pairs.get("day"));
		for(StringDoublePair pair: day){
			DayMultiplier.put(DayType.valueOf(pair.First()), pair.Second());
		}
		return getInstance();
	}
}
