package main.com.entities;

import java.util.*;

public class Movie {

	public String movie_title;
	public String movie_status;
	public String movie_synopsis;
	public String movie_director;
	public List<String> cast = new ArrayList<>();
    public Reviews new_review;
	private String _uuid;
	public String getUUID(){
		return _uuid;
	}
	
	public Movie(String movie_title,String movie_status,String movie_synopsis,String movie_director,ArrayList<String> cast)
	{
		this.movie_title=movie_title;
		this.movie_status=movie_status;
		this.movie_synopsis=movie_synopsis;
		this.movie_director=movie_director;
		this.cast=cast;
		this.new_review=new_review;
	}

	public Movie(String movie_title,int duration, RatingEnum rating, StatusEnum movie_status,String movie_synopsis,String movie_director, List<String> cast)
	{
		//TODO: Implement
		this.movie_title=movie_title;
		this.movie_synopsis=movie_synopsis;
		this.movie_director=movie_director;
		this.cast=cast;
	}

	public void addReview(String review,double rating)
	{
		new_review.addReview(review,rating);
	}
	public double calculateOverallRating()
	{
		return(new_review.sum_of_ratings/new_review.number_of_reviews);
	}
	
}
