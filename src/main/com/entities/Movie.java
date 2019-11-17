package main.com.entities;

import main.com.utils.ISerialisable;
import static main.com.utils.SerialisationUtils.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/** This class holds the details of a movie
 * @author SS1 Group 6
 * @version 13
 */

public class Movie implements ISerialisable {
	 /** name of this movie
     */
	public String movie_title;
	
	 /** duration of this movie
     */
	public int durationMin;
	
	 /** movie status of this movie
     */
	public StatusEnum movieStatus;
	
	 /** rating of this movie
     */
	public RatingEnum rating;
	
	 /** synopsis of this movie
     */
	public String movie_synopsis;
	
	 /** director of this movie
     */
	public String movie_director;
	
	 /** casts of this movie
     */
	public List<String> cast;
	
	 /** create a review store object
     */
    public ReviewStore reviewStore = new ReviewStore();
	private UUID _uuid;
	/**
	*get uuid
	 * @return the UUID of the movie
	*/
	public String getUUID(){
		return _uuid.toString();
	}
/**
* default movie constructor
*/
	public Movie(){}

    /** Creates a new movie  with the details
     * @param movie_title		name of the movie
     * @param duration			duration of the movie
     * @param rating			rating of the movie
     * @param movieStatus		status of a movie
     * @param movie_synopsis			synopsis of a movie
     * @param movie_director			director of a movie
     * @param cast				cast of a movie
     */
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

    /** Creates a new movie  with the details
	 * @param uuid				Movie UUID
     * @param movie_title		name of the movie
     * @param duration			duration of the movie
     * @param rating			rating of the movie
     * @param movieStatus		status of the movie
     * @param movie_synopsis			synopsis of the movie
     * @param movie_director			director of the movie
     * @param cast				cast of the movie
     * @param reviews		reviews of the movie
     *?@params uuid - store the uuid
     */
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

	 /** Method to add review
	  * @param review 		review of the movie given by movie goer
	  * @param rating		rating of the movie given by movie goer
     */
	public void addReview(String review,double rating)
	{
		reviewStore.addReview(review,rating);
	}

	 /** Method to add review
	  * @param index		which position the review is stored
	  * @return True/False if a review is successfully removed
    */
	public boolean removeReview(int index){
		if (reviewStore.removeReview(index)) return true;
		return false;
	}
	
	 /** Method to calculate overall rating
	  * @return the average rating based on all the ratings given
    */
	public double calculateOverallRating()
	{
		return reviewStore.calculateOverallRating();
	}

    /**
     * Serialize movie
     * @return serialised string
     */
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

    /**
     * Deserialize movie
     */
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
