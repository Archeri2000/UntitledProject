package main.com.entities;

import main.com.utils.ISerialisable;
import static main.com.utils.SerialisationUtils.*;


import java.util.*;

/** This class holds the list of reviews with some relevant function
 * @author SS1 Group 6
 * @version 13
 */
public class ReviewStore implements ISerialisable {
    /** To create a list for the reviews
     */
	public List<Review> reviews = new ArrayList<>();
	public ReviewStore() {}

    /** To allow for the addition of a review
     * @return True/False if the review is successfully added
     */
	public boolean addReview(String new_review,double rating)
	{
		if(rating < 10 && rating > 0) {
			reviews.add(new Review(new_review, rating));
			return true;
		}
		return false;
	}

	
    /** To allow for the removal of a review
     * @return True/False if the review is successfully removed
     */
	public boolean removeReview(int index){
		try {
			reviews.remove(index-1);
			return true;
		}catch (ArrayIndexOutOfBoundsException e){
			return  false;
		}
	}


    /** To calculate overall rating given by movier goers
     * @return the average rating given by movier goers
     */
	public Double calculateOverallRating(){
		double val = 0;
		for (Review r: reviews) {
			val += r.Second();
		}
		return val/ reviews.size();
	}

	
	 /**
     * Serialize reviews
     * @return serialised string
     */
	@Override
	public String toSerialisedString() {
		return serialise(
				serialiseList(reviews, "reviews")
		);
	}


    /** Deserialize reviews
     */
	@Override
	public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
		HashMap<String, String> pairs = deserialise(s);
		ReviewStore r = new ReviewStore();
		r.reviews = deserialiseList(Review.class, pairs.get("reviews"));
		return r;
	}
}
