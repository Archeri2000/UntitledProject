package main.com.moviebooking;

import java.util.*;
import java.io.*;
public class Reviews {
	public  ArrayList<String> review=new ArrayList();
	public  double sum_of_ratings;
	public  int number_of_reviews;
	public Reviews()
	{
		sum_of_ratings=0;
		number_of_reviews=0;
	}
	public void addReview(String new_review,double rating)
	{
		review.add(new_review);
		sum_of_ratings+=rating;
		number_of_reviews++;
	}
	
}
