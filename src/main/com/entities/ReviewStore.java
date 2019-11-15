package main.com.entities;

import main.com.utils.ISerialisable;
import static main.com.utils.SerialisationUtils.*;


import java.util.*;

public class ReviewStore implements ISerialisable {
	public List<Review> reviews = new ArrayList<>();
	public ReviewStore()
	{
	}
	public void addReview(String new_review,double rating)
	{
		reviews.add(new Review(new_review, rating));
	}

	public Double calculateOverallRating(){
		double val = 0;
		for (Review r: reviews) {
			val += r.Second();
		}
		return val/ reviews.size();
	}

	@Override
	public String toSerialisedString() {
		return serialise(
				serialiseList(reviews, "reviews")
		);
	}

	@Override
	public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
		HashMap<String, String> pairs = deserialise(s);
		ReviewStore r = new ReviewStore();
		r.reviews = deserialiseList(Review.class, pairs.get("reviews"));
		return r;
	}
}
