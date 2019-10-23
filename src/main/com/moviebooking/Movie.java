package com.moviebooking;
import jdk.jshell.spi.ExecutionControl;

import java.util.*;
import java.io.*;
public class Movie {

	public String movie_title;
	public String movie_status;
	public String movie_synopsis;
	public String movie_director;
	public ArrayList<String> cast = new ArrayList();;
	public Reviews new_review;
	private String _uuid;
	public String getUUID(){
		return _uuid;
	}
	
	public Movie(String movie_title,String movie_status,String movie_synopsis,String movie_director,ArrayList<String> cast,Reviews new_review)
	{
		this.movie_title=movie_title;
		this.movie_status=movie_status;
		this.movie_synopsis=movie_synopsis;
		this.movie_director=movie_director;
		this.cast=cast;
		this.new_review=new_review;
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
