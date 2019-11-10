package assignment;
import java.util.*;
public class PriceRepository {
	public HashMap<AgeGroup,float> AgeMultipliers=new HashMap<AgeGroup,float>();
	public HashMap<CinemaType, float> CinemaTypeMultipliers =new HashMap<>() ;
	public HashMap<ShowingEnum,float> MoviePrices =new HashMap<>() ;
	public HashMap<DayType, float> DayMultiplier=new HashMap<>();
	
	
	private String toSerialisedString()
	{
		return "";
	}
	private String fromSerialisedString(String s)
	{
		return s;
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
		CinemaTypeMultipliers.put(cinema,multiplier)
		return true;
	}
	private boolean setMoviePrice(ShowingEnum showing, float price)
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
	private float GetPrice(AgeGroup age, CinemaType cinema, ShowingEnum showing, DayType day)
	{
		float price=0;
		float age_price=AgeMultipliers.get(age);
		float cinema_price=CinemaTypeMultipliers.get(cinema);
		float showing_price=MoviePrices.get(showing);
		float day_price=DayMultiplier.get(day);
		price=age_price*cinema_price*showing_price*day_price;
		return price;
		
	}
	
}
