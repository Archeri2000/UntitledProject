package main.com.view;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.com.entities.Movie;
import main.com.entities.RatingEnum;
import main.com.entities.StatusEnum;
import main.com.services.CineplexManagementService;
import main.com.services.MovieManagementService;
import main.com.services.PriceManagementService;
import main.com.services.ShowingManagementService;

public class AdminView
{
	
	private static boolean login_status=false;
	
	private Login LoginModule = new Login();
	
	private MovieManagementService movie_manager=new MovieManagementService();
	ShowingManagementService show_manager=new ShowingManagementService();
	PriceManagementService price_manager=new PriceManagementService();
	private CineplexManagementService cineplex_manager=new CineplexManagementService();
	
	
	
	private Scanner sc=new Scanner(System.in);
	private void optionsMenu()
	{
		
		System.out.println("Welcome to the Admin panel");
		while(!login_status) {
			System.out.println("Enter 0 to return");
			System.out.println("Enter 1 to Login");
			System.out.print("Enter user id... ");
			String user_id = sc.next();
			System.out.print("Enter password...");
			String password = sc.next();
			if (LoginModule.checkLogin(user_id, password)) {
				System.out.println("Logged in successfully");
				login_status = true;
			} else
				System.out.println("Incorrect login id or password");
		}

		int choice;
		while(login_status)
		{
			System.out.println();
			System.out.println("Enter 0 to view all staff");
			System.out.println("Enter 1 to change password");
			System.out.println("Enter 2 to add staff");
			System.out.println("Enter 3 to manage movies");
			System.out.println("Enter 4 to manage show times");
			System.out.println("Enter 5 to manage price of movies");
			System.out.println("Enter 6 to manage cineplexes");
			System.out.println("Enter -1 to log out");
			System.out.println();
			System.out.print("Enter your choice......  ");
			choice=sc.nextInt();
			if(choice == 0)
			{
				System.out.println("Staff Members:");
				for(String staff: LoginModule.getStaff()){
					System.out.println(staff);
				}
			}
			else if(choice == 1)
			{
				System.out.print("Enter user id... ");
				String user_id=sc.next();
				System.out.print("Enter new password...");
				String password=sc.next();
				while(!LoginModule.checkPassword(password))
				{
					System.out.println("Password not strong enough please enter again");
					password=sc.next();
				}
				System.out.print("Confirm password...");
				String new_password=sc.next();
				if(LoginModule.changePassword(user_id,password,new_password))
					System.out.println("Password changed successfully.");
				else{
					System.out.println("Passwords did not match!");
				}
			}
			else if(choice == 2)
			{
				System.out.print("Enter user id... ");
				String user_id=sc.next();
				System.out.print("Enter password...");
				String password=sc.next();
				while(!LoginModule.checkPassword(password))
				{
					System.out.println("Password not strong enough please enter again");
					password=sc.next();
				}
				if(!LoginModule.addStaff(user_id,password))
					System.out.println("User already exists");
				else{
					System.out.println("Staff added successfully");
				}
			}
			else if(choice==3) {
				manageMovies();
			}else if(choice==4) {
				manageShowTimes();
			}else if(choice==5) {
				managePrice();
			}else if(choice==6) {
				manageCineplexes();
			}else if(choice==-1){
				login_status=false;
				System.out.println("Logged out successfully");
			}
			else
			{
				System.out.println("Invalid choice");
			}
		}
		System.out.println("Thank you for your visit");
		
	}
	private void manageMovies()
	{
		System.out.println("Welcome to Movie Management Service");
		System.out.println();
		int choice = 10;
		while(choice != -1)
		{
			System.out.println("Enter 1 to add a movie");
			System.out.println("Enter 2 to set movie status");
			System.out.println("Enter 3 to set movie rating certification");
			System.out.println("Enter 4 to remove a movie");
			System.out.println("Enter 5 to set movie synopsis");
			System.out.println("Enter 6 to set movie director");
			System.out.println("Enter 7 to add movie cast ");
			System.out.println("Enter 8 to remove movie cast");
			System.out.println("Enter 9 to get details of a movie");
			System.out.println("Enter -1 to exit");
			System.out.println();
			System.out.print("Enter your choice...  ");
			choice=sc.nextInt();
			if(choice==1)
			{
				System.out.print("Enter movie title...");
				String title=sc.nextLine();
				System.out.println();
				int duration = -1;
				while(duration <= 0) {
					System.out.print("Enter movie duration...");
					duration =sc.nextInt();
				}
				RatingEnum rating = getRating();
				StatusEnum status = getStatus();
				System.out.print("Enter movie synopsis...");
				String synopsis=sc.nextLine();
				System.out.print("Enter movie director...");
				String director=sc.nextLine();
				System.out.println("Enter movie cast and enter -99 to stop");
				String cast=sc.nextLine();
				ArrayList<String> cast_members= new ArrayList<>();
				while(!cast.equals("-99"))
				{
					cast_members.add(cast);
					System.out.println("Enter movie cast and enter -99 to stop");
					cast=sc.nextLine();
				}
				movie_manager.addMovie(title,duration, rating,status,synopsis,director,cast_members);
			}
			else if(choice==2)
			{
				Movie movie = getMovie();
				if (movie != null)
				{
					System.out.println("Current Status: " + movie.movieStatus.name());
					StatusEnum status = getStatus();
					movie_manager.setMovieStatus(movie,status);
				}
			}
			else if(choice==3)
			{
				Movie movie = getMovie();
				if (movie != null)
				{
					System.out.println("Current Rating: " + movie.rating.name());
					RatingEnum rating = getRating();
					movie_manager.setMovieRating(movie,rating);//ENUM
				}
			}
			else if(choice==4)
			{
				Movie movie = getMovie();
				if (movie != null)
				{
					movie_manager.removeMovie(movie);//movie object
				}
			}
			else if(choice==5)
			{
				Movie movie = getMovie();
				if (movie != null)
				{
					System.out.println("Current Synopsis: " + movie.movie_synopsis);
					System.out.print("Enter movie synopsis");
					String synopsis=sc.nextLine();
					movie_manager.setMovieSynopsis(movie,synopsis);
				}
			}
			else if(choice==6)
			{
				Movie movie = getMovie();
				if (movie != null)
				{
					System.out.println("Current Director: " + movie.movie_director);
					System.out.print("Enter movie director: ");
					String movie_director=sc.nextLine();
					movie_manager.setMovieDirector(movie,movie_director);
				}
			}
			else if(choice==7)
			{
				Movie movie = getMovie();
				if (movie != null)
				{
					System.out.println("Current cast members:");
					for(String name: movie.cast){
						System.out.println(name);
					}
					System.out.print("Enter movie cast member name: ");
					String movie_cast = sc.nextLine();
					movie_manager.addMovieCast(movie,movie_cast);
				}
			}
			else if(choice==8)
			{
				Movie movie = getMovie();
				if (movie != null)
				{
					System.out.println("Current cast members:");
					for(String name: movie.cast){
						System.out.println(name);
					}
					System.out.print("Enter movie cast member name to remove: ");
					String movie_cast=sc.nextLine();
					movie_manager.removeMovieCast(movie,movie_cast);//movie object
				}
			}
			else if(choice==9)
			{
				Movie movie = getMovie();
				if(movie != null){
					System.out.println("Title: " + movie.movie_title);
					System.out.println("Duration: " + movie.durationMin + " min");
					System.out.println("Status: " + movie.movieStatus.name());
					System.out.println("Rating: " + movie.rating.name());
					System.out.println("Director: " + movie.movie_director);
					System.out.println("Cast Members:");
					for(String name: movie.cast) {
						System.out.println(name);
					}
					System.out.println("Review Score: " + movie.calculateOverallRating());
				}
			}
			else
			{
				System.out.println("Invalid choice");
			}
		}
	}

	private Movie getMovie(){
		System.out.print("Enter movie title...");
		String movie_title=sc.nextLine();
		List<Movie> movieList = movie_manager.getMovie(movie_title);
		System.out.println("Movies found:");
		for (Movie value : movieList) {
			System.out.println(value.movie_title);
		}
		System.out.print(" Enter exact name: ");
		String movie_title2 = sc.nextLine();
		for(Movie value: movieList){
			if(movie_title2.equalsIgnoreCase(value.movie_title)){
				return value;
			}
		}
		System.out.println("Invalid Movie Name!");
		return null;
	}

	private StatusEnum getStatus() {
		while (true) {
			System.out.print("Enter movie status... ComingSoon, NotShowing, Showing");
			String s2 = sc.next();
			for (StatusEnum e : StatusEnum.values()) {
				if (s2.equalsIgnoreCase(e.name())) {
					return e;
				}
			}
			System.out.println("Invalid entry!");
		}
	}

	private RatingEnum getRating() {
		while(true) {
			System.out.print("Enter movie rating certification A for adult U for Universal UA for universal adult...");
			String r1 = sc.next();
			for (RatingEnum e : RatingEnum.values()) {
				if (r1.equalsIgnoreCase(e.name())) {
					return e;
				}
			}
			System.out.println("Invalid entry!");
		}
	}
	
	private void manageShowTimes()
	{
		System.out.println("Welcome to Showtime management service");
		System.out.println();
		System.out.println("Enter 1 to enter showtime");
		System.out.println("Enter 2 to remove showtime");
		System.out.println("Enter -1 to exit");
	
		System.out.print("Enter your choice...  ");
		int choice=sc.nextInt();
		while(choice!=-1)
		{
			if(choice==1)
			{
				System.out.print("Enter movie...  ");
				System.out.println();
				System.out.print("Enter cinema...  ");
				System.out.println();
				System.out.print("Enter date-time...  ");
				System.out.println();
				System.out.print("Enter show status...  ");
				System.out.println();
				//call the AddShowtime method from ShowingManagementService class
			}
			if(choice==2)
			{
				System.out.println("Enter cinema...  ");
				System.out.println();
				System.out.print("Enter showtime... ");
				//call removeShowTime method from the ShowingManagementService class
			}
			else
			{
				System.out.println("Invalid choice");
			}
			System.out.println("Enter 1 to enter showtime");
			System.out.println("Enter 2 to remove showtime");
			System.out.println("Enter -1 to exit");
			System.out.print("Enter your choice...  ");
		    choice=sc.nextInt();
		}
		System.out.println("Thanyou for choosing this service");
	}
	
	
	
	private void managePrice()
	{
		System.out.println("Welcome to Price Management Service");
		System.out.println();
		System.out.println("Enter 1 to enter price according to cinema");
		System.out.println("Enter 2 to enter price according to movie type");
		System.out.println("Enter 3 to enter price according to age");
		System.out.println("Enter 4 to enter price according to day");
		System.out.println("Enter 5 to enter price according to public holiday");
		System.out.println("Enter 6 to check price according to cinema");
		System.out.println("Enter 7 to check price according to movie type");
		System.out.println("Enter 8 to check price according to age");
		System.out.println("Enter 9 to check price according to day");
		System.out.println("Enter -1 to exit");
		System.out.println();
		System.out.print("Enter your choice...");
		int choice=sc.nextInt();
		System.out.println();
		
		while(choice!=-1)
		{
			if(choice==1)
			{
				System.out.println("Enter cinema type..");
				System.out.println("Enter price");
				// call SetCinemaTypeMultiplier from PriceManagementService class
			}
			if(choice==2)
			{
				System.out.println("Enter movie type..");
				System.out.println("Enter price");
				// call SetMovieTypePrice from PriceManagementService class
			}
			if(choice==3)
			{
				System.out.println("Enter age type..");
				System.out.println("Enter price");
				// call SetAgeGroupMultiplier from PriceManagementService class
			}
			if(choice==4)
			{
				System.out.println("Enter day type..");
				System.out.println("Enter price");
				// call SetDayTypeMultiplier from PriceManagementService class
			}
			if(choice==5)
			{
				System.out.println("Enter public holiday..");
				
				// call AddPublicHoliday from PriceManagementService class
			}
			if(choice==6)
			{
				// call GetCinemaTypeMultiplier from PriceManagementService class
			}
			if(choice==7)
			{
				// call GetMovieTypePrice from PriceManagementService class
			}
			if(choice==8)
			{
				// call GetAgeGroupMultiplier from PriceManagementService class
				
			}
			if(choice==9)
			{
				// call GetDayTypeMultiplier from PriceManagementService class
				
			}
			else
			{
				System.out.println("Invalid choice");
			}
			System.out.println();
			System.out.println("Enter 1 to enter price according to cinema");
			System.out.println("Enter 2 to enter price according to movie type");
			System.out.println("Enter 3 to enter price according to age");
			System.out.println("Enter 4 to enter price according to day");
			System.out.println("Enter 5 to enter price according to public holiday");
			System.out.println("Enter 6 to check price according to cinema");
			System.out.println("Enter 7 to check price according to movie type");
			System.out.println("Enter 8 to check price according to age");
			System.out.println("Enter 9 to check price according to day");
			System.out.println("Enter -1 to exit");
			System.out.println();
			System.out.print("Enter your choice...");
		    choice=sc.nextInt();
			System.out.println();
			
		}
		System.out.println("Thanyou for using price management service");
	}
	private void manageCineplexes()
	{
		System.out.println("Welcome to Cineplex Management Service");
		System.out.println();
		System.out.println("Enter 1 to add cineplex");
		System.out.println("Enter 2 to remove cineplex");
		System.out.println("Enter 3 to add cinema");
		System.out.println("Enter 4 to remove cinema");
		System.out.println("Enter -1 to exit");
		System.out.println();
		System.out.print("Enter your choice... ");
		int choice=sc.nextInt();
		while(choice!=-1)
		{
			if(choice==1)
			{
				System.out.println("Enter Cineplex name");
				String cineplex=sc.nextLine();
				// call AddCineplex method from CineplexManagementService class
				cineplex_manager.AddCineplex(cineplex);
			}
			if(choice==2)
			{
				System.out.println("Enter Cineplex name");
				String cineplex=sc.nextLine();
				boolean b=cineplex_manager.RemoveCineplex(cineplex);
				if(b==true)
					System.out.println("Cineplex removed successfully");
				else
					System.out.println("Cinplex not found");
				System.out.println();
			}
			if(choice==3)
			{
				System.out.println("Enter Cineplex name");
				String cineplex=sc.nextLine();
				System.out.println("Enter cinema name");
				String cinema=sc.nextLine();
				// call AddCinema method from CineplexManagementService class
			}
			if(choice==4)
			{
				System.out.println("Enter Cineplex name");
				String cineplex=sc.nextLine();
				System.out.println("Enter cinema name");
				String cinema=sc.nextLine();
				// call RemoveCinema method from CineplexManagementService class
			}
			else
			{
				System.out.println("Invalid Choice");
			}
			System.out.println();
			System.out.println("Enter 1 to add cineplex");
			System.out.println("Enter 2 to remove cineplex");
			System.out.println("Enter 3 to add cinema");
			System.out.println("Enter 4 to remove cinema");
			System.out.println("Enter -1 to exit");
			System.out.println();
			System.out.print("Enter your choice... ");
			choice=sc.nextInt();
			System.out.println();
		}
		System.out.println("Thanyou for using this service");
				
				
	}
	
	public static void main(String[] sd)
	{
		AdminView ob=new AdminView();
		ob.optionsMenu();
	}
}
