package main.com.repositories;

import main.com.entities.*;
import main.com.utils.ISerialisable;
import main.com.utils.StringDoublePair;
import main.com.utils.StringIntPair;

import static main.com.utils.SerialisationUtils.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/** This class acts as a repository for storing price of movie tickets 
 * @author SS1 Group 6
 * @version 13
 */
public class PriceRepository implements ISerialisable {
	/**
	 * Creation of a hashmap to store agesgroups-multipliers for prices
	 */
	public HashMap<AgeGroup, Double> AgeMultipliers = new HashMap<>();
	/**
	 * Creation of a hashmap to store cinematype-multipliers for prices
	 */
	public HashMap<CinemaType, Double> CinemaTypeMultipliers = new HashMap<>();
	/**
	 * Creation of a hashmap to store showingstatus-multipliers for prices
	 */
	public HashMap<ShowingEnum,Double> MoviePrices = new HashMap<>();
	/**
	 * Creation of a hasHmap to store daytype-multipliers(weekday/weekend/holiday) for prices
	 */
	public HashMap<DayType, Double> DayMultiplier = new HashMap<>();
	
	/**
	 * Creation of a hashmap to store a list of public holidays
	 */
	public List<LocalDate> Public_Holidays = new ArrayList<>();
	
	/**
	 * Set age multipliers 
	 * @param age- age of the customer
	 * @param multiplier- price multiplier for the agegroup
	 * @return boolean value for verification of storage
	 */
	
	public boolean setAgeMultipliers(AgeGroup age, double multiplier)
	{
		AgeMultipliers.put(age,multiplier);
		return true;
	}
	/**
	 * Set cinema multipliers
	 * @param cinema - cinema for which the multiplier has to be set
	 * @param multiplier - price multiplier for the specific cinema
	 * @return boolean value for verification of cinema-type-multiplier  storage
	 */
	public boolean setCinemaMultipliers(CinemaType cinema, double multiplier)
	{
		CinemaTypeMultipliers.put(cinema,multiplier);
		return true;
	}
	/**
	 * Set movie price based on showing status
	 * @param showing - showing status for which price has to be set
	 * @param price - price of the movie
	 * @return boolean value for verification of storage
	 */
	public boolean setMoviePrice(ShowingEnum showing, double price)
	{
		MoviePrices.put(showing,price);
		return true;
	}
	/**
	 * Set multiplier for a specific day-type(weekend/weekday/holiday)
	 * @param day - day for which multiplier has to be set
	 * @param multiplier - price multiplier for the specific day
	 * @return boolean value for verification of day-multiplier storage
	 */
	public boolean setDayMultipliers(DayType day, double multiplier)
	{
		DayMultiplier.put(day,multiplier);
		return true;
	}
	/**
	 * Add a public holiday to the Public_Holidays hashmap
	 * @param day- variable that contains public holiday date
	 * @return boolean value for verification of holiday storage
	 */

	public boolean addPublicHoliday(LocalDate day)
	{
		if(Public_Holidays.contains(day)) return false;
		Public_Holidays.add(day);
		return true;
	}
	/**
	 * Remove a public holiday to the Public_Holidays hashmap
	 * @param day- variable that contains public holiday date
	 * @return boolean value for verification of removal of holiday date
	 */

	public boolean removePublicHoliday(LocalDate day)
	{
		if(!Public_Holidays.contains(day)) return false;
		Public_Holidays.remove(day);
		return true;
	}
	/** 
	 * get the price of ticket using  multiplier like age and showing status of movie
	 * @param age- age of customer
	 * @param showing- showing status of the movie
	 * @return (returns the value of GetPrice(age, showing.getCinemaType(), showing.getShowType(), showing.getShowing_time())) 
	 */
	
	

	public double getPrice(int age, MovieShowing showing){
		return GetPrice(age, showing.getCinemaType(), showing.getShowType(), showing.getShowing_time());
	}
	
	/** 
	 * get the price of ticket using  multiplier like age and showing status of movie and day(weekday/weekend/holiday)
	 * @param age- age of customer
	 * @param showing- showing status of the movie
	 * @param cinema - reference of cinema object
	 * @param day- used to store day(weekday/weekend/holiday)
	 * @return value obtained from(GetPrice(a, cinema, showing, d))
	 */
	

	public double GetPrice(int age, CinemaType cinema, ShowingEnum showing, LocalDateTime day){
		DayType d;
		if(Public_Holidays.contains(day.toLocalDate())){
			d = DayType.Sunday_PH;
		}else{
			d = DayType.getDayType(day);
		}
		AgeGroup a = AgeGroup.getGroup(age);
		return GetPrice(a, cinema, showing, d);
	}
	
	/**
	 * Get the total price based on the various multipliers
	 * @param age - age group of the customer
	 * @param cinema - cinema of booking
	 * @param showing - showing status of the movie
	 * @param day - day-type(weekend/weekday/holiday)
	 * @return the total price obtained by multiplying all the multipliers
	 */
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

	/**
	 * Get the instance of PriceRepository
	 * @return instance of the PriceRepository object (_static_manager)
	 */
	private static PriceRepository _static_manager;
	public static PriceRepository getInstance(){
		if(_static_manager == null){
			_static_manager = new PriceRepository();
		}
		return _static_manager;
	}
	/**
	 * PriceRepository constructor to initilise age,type,showing,day
	 */
	public PriceRepository(){
		for(AgeGroup age:AgeGroup.values()){
			AgeMultipliers.put(age, 1.0);
		}
		for(CinemaType type: CinemaType.values()){
			CinemaTypeMultipliers.put(type, 1.0);
		}
		for(ShowingEnum showing: ShowingEnum.values()){
			MoviePrices.put(showing, 10.0);
		}
		for(DayType day: DayType.values()){
			DayMultiplier.put(day, 1.0);
		}
	}
	//TODO
	/**
	 * Serialization method
	 * @return serialized String
	 * 
	 */
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

	/**
	 * Deserialize a string s
	 * @param s - string to be deserialized
	 * @return getInstance()
	 */
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
