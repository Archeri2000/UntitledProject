package main.com.entities;

import main.com.utils.ISerialisable;
import static main.com.utils.SerialisationUtils.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Movie implements ISerialisable {

	public String movie_title;
	public int durationMin;
	public StatusEnum movieStatus;
	public RatingEnum rating;
	public String movie_synopsis;
	public String movie_director;
	public List<String> cast;
    public ReviewStore reviewStore = new ReviewStore();
	private UUID _uuid;
	public String getUUID(){
		return _uuid.toString();
	}

	public Movie(){}

	public Movie(String movie_title, int duration, RatingEnum rating, StatusEnum movieStatus, String movie_synopsis, String movie_director, List<String> cast)
	{
		this.movie_title=movie_title;
		this.durationMin = duration;
		this.rating = rating;
		this.movieStatus = movieStatus;
		this.movie_synopsis=movie_synopsis;
		this.movie_director=movie_director;
		this.cast=cast;
		_uuid = UUID.randomUUID();
	}

	public Movie(String movie_title, int duration, RatingEnum rating, StatusEnum movieStatus, String movie_synopsis, String movie_director, List<String> cast, UUID uuid, ReviewStore reviews)
	{
		this.movie_title=movie_title;
		this.durationMin = duration;
		this.rating = rating;
		this.movieStatus = movieStatus;
		this.movie_synopsis=movie_synopsis;
		this.movie_director=movie_director;
		this.cast=cast;
		_uuid = uuid;
		this.reviewStore = reviews;
	}

	public void addReview(String review,double rating)
	{
		reviewStore.addReview(review,rating);
	}

	public double calculateOverallRating()
	{
		return reviewStore.calculateOverallRating();
	}

	@Override
	public String toSerialisedString() {
		return serialise(
				serialiseString(getUUID(), "uuid"),
				serialiseString(movie_title, "title"),
				serialiseInt(durationMin, "duration"),
				serialiseString(rating.name(), "rating"),
				serialiseString(movieStatus.name(), "status"),
				serialiseString(movie_synopsis, "synopsis"),
				serialiseString(movie_director, "director"),
				serialiseStringList(cast, "cast"),
				serialiseObject(reviewStore, "reviews")
		);
	}

	@Override
	public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException{
		HashMap<String, String> pairs = deserialise(s);
		assert pairs != null;
		UUID uuid = UUID.fromString(deserialiseString(pairs.get("uuid")));
		String title = deserialiseString(pairs.get("title"));
		int duration = deserialiseInt(pairs.get("duration"));
		RatingEnum rating = RatingEnum.valueOf(deserialiseString(pairs.get("rating")));
		StatusEnum status = StatusEnum.valueOf(deserialiseString(pairs.get("status")));
		String synopsis = deserialiseString(pairs.get("synopsis"));
		String director = deserialiseString(pairs.get("director"));
		List<String> cast = deserialiseStringList(pairs.get("cast"));
		ReviewStore reviews;
		try{
			reviews = deserialiseObject(ReviewStore.class, pairs.get("reviews"));
		}catch (Exception e){
			throw new InvalidPropertiesFormatException("");
		}
		return new Movie(title, duration, rating, status, synopsis, director, cast, uuid, reviews);
	}
}
