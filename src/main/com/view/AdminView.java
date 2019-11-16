package main.com.view;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import main.com.entities.*;
import main.com.services.*;

public class AdminView
{
	
	private static boolean login_status=false;
	
	private final Login LoginModule = new Login();
	
	private final MovieManagementService movie_manager=new MovieManagementService();
	private final ShowingManagementService show_manager=new ShowingManagementService();
	private final PriceManagementService price_manager=new PriceManagementService();
	private final CineplexManagementService cineplex_manager = new CineplexManagementService();
	private final CineplexQueryService cineplex_query = new CineplexQueryService();
	
	
	
	private final Scanner sc=new Scanner(System.in);
	public void optionsMenu()
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
			sc.nextLine();
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
		int choice;
		while(true)
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
			else if(choice == -1){
				return;
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

	private ShowingEnum getShowType() {
		while(true) {
			System.out.print("Enter movie type (IMAX, Full3D, Digital): ");
			String r1 = sc.next();
			for (ShowingEnum e : ShowingEnum.values()) {
				if (r1.equalsIgnoreCase(e.name())) {
					return e;
				}
			}
			System.out.println("Invalid entry!");
		}
	}

	private CinemaType getCinemaType() {
		while(true) {
			//TODO: Add into brackets the types
			System.out.print("Enter cinema type (): ");
			String r1 = sc.next();
			for (CinemaType e : CinemaType.values()) {
				if (r1.equalsIgnoreCase(e.name())) {
					return e;
				}
			}
			System.out.println("Invalid entry!");
		}
	}

	private AgeGroup getAgeGroup() {
		while(true) {
			//TODO: Add into brackets the types
			System.out.print("Enter age group (): ");
			String r1 = sc.next();
			for (AgeGroup e : AgeGroup.values()) {
				if (r1.equalsIgnoreCase(e.name())) {
					return e;
				}
			}
			System.out.println("Invalid entry!");
		}
	}

	private DayType getDayType() {
		while(true) {
			//TODO: Add into brackets the types
			System.out.print("Enter day type (): ");
			String r1 = sc.next();
			for (DayType e : DayType.values()) {
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
		int choice;
		Cineplex cineplex = getCineplex();
		if(cineplex != null) {
			Cinema cinema = getCinema(cineplex);
			if(cinema != null) {
				while (true) {
					System.out.println("Enter 1 to create movie showing");
					System.out.println("Enter 2 to remove movie showing");
					System.out.println("Enter -1 to exit");
					System.out.print("Enter your choice...  ");
					choice = sc.nextInt();
					sc.nextLine();
					if (choice == 1) {
						Movie movie = getMovie();
						if (movie == null) {
							continue;
						}
						LocalDateTime showingTime = getDateTime();
						ShowingEnum showType = getShowType();
						if(show_manager.addShowing(cinema, movie, showingTime, showType) == null){
							System.out.println("Unable to add movie showing!");
						}
					}
					else if (choice == 2) {
						MovieShowing showing = getShowtime(cinema);
						if(showing != null){
							show_manager.removeShowing(cinema, showing);
						}
					}
					else if (choice == -1) {
						System.out.println("Thank you for choosing this service");
						return;
					} else {
						System.out.println("Invalid choice");
					}
				}
			}
		}
	}

	private LocalDateTime getDateTime(){
		System.out.println("Enter Date and Time in format: YYYY-MM-DD HH:MM");
		String str = sc.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return LocalDateTime.parse(str, formatter);
	}

	private Cineplex getCineplex() {
		List<Cineplex> cineplexList = cineplex_query.GetCineplexes();
		System.out.println("Cineplexes found:");
		if (!cineplexList.isEmpty()) {
			for (Cineplex value : cineplexList) {
				System.out.println(value.getCineplexName());
			}
			System.out.print(" Enter exact name: ");
			String cineplex_name = sc.nextLine();
			for (Cineplex value : cineplexList) {
				if (cineplex_name.equalsIgnoreCase(value.getCineplexName())) {
					return value;
				}
			}
		}
			System.out.println("Cineplex not found");
			return null;
	}

	private Cinema getCinema(Cineplex cineplex) {
		List<Cinema> Cinemas = cineplex_query.GetCinemas(cineplex);
		System.out.println("Cinemas:");
		for (Cinema value : Cinemas) {
			System.out.println(value.getName());
		}
		System.out.print(" Enter exact name: ");
		String cineplex_name = sc.nextLine();
		for(Cinema value: Cinemas){
			if(cineplex_name.equalsIgnoreCase(value.getName())){
				return value;
			}
		}
		System.out.println("Invalid Cinema Name!");
		return null;
	}

	private MovieShowing getShowtime(Cinema cinema){
		List<MovieShowing> Cinemas = cineplex_query.GetShows(cinema);
		System.out.println("Showings:");
		LocalDateTime now = LocalDateTime.now();
		for (MovieShowing value : Cinemas) {
			if(value.getShowing_time().isAfter(now)) {
				System.out.println(value.getShowing_time() + " : " + value.getShownMovieTitle());
			}
		}
		LocalDateTime time = getDateTime();
		for(MovieShowing value: Cinemas){
			if(time.equals(value.getShowing_time())){
				return value;
			}
		}
		System.out.println("Invalid Showing Time!");
		return null;
	}


	private void managePrice()
	{
		System.out.println("Welcome to Price Management Service");
		int choice;
		while(true)
		{
			System.out.println();
			System.out.println("Enter 1 to enter price according to cinema");
			System.out.println("Enter 2 to enter price according to showing type");
			System.out.println("Enter 3 to enter price according to age");
			System.out.println("Enter 4 to enter price according to day");
			System.out.println("Enter 5 to add public holidays");
			System.out.println("Enter 6 to check price according to cinema");
			System.out.println("Enter 7 to check price according to movie type");
			System.out.println("Enter 8 to check price according to age");
			System.out.println("Enter 9 to check price according to day");
			System.out.println("Enter -1 to exit");
			System.out.println();
			System.out.print("Enter your choice...");
			choice=sc.nextInt();
			if(choice==1)
			{
				CinemaType cinemaType = getCinemaType();
				System.out.println("Enter price multiplier");
				float mult = sc.nextFloat();
				price_manager.SetCinemaTypeMultiplier(cinemaType, mult);
			}
			else if(choice==2)
			{
				ShowingEnum showType = getShowType();
				System.out.println("Enter price multiplier");
				float mult = sc.nextFloat();
				price_manager.SetMovieTypePrice(showType, mult);
			}
			else if(choice==3)
			{
				AgeGroup ageGroup = getAgeGroup();
				System.out.println("Enter price multiplier");
				float mult = sc.nextFloat();
				price_manager.SetAgeGroupMultiplier(ageGroup, mult);
			}
			else if(choice==4)
			{
				DayType dayType = getDayType();
				System.out.println("Enter price");
				System.out.println("Enter price multiplier");
				float mult = sc.nextFloat();
				price_manager.SetDayTypeMultiplier(dayType, mult);
			}
			else if(choice==5)
			{
				System.out.println("Enter public holiday..");
				// TODO: call AddPublicHoliday from PriceManagementService class
			}
			else if(choice==6)
			{
				HashMap<CinemaType, Float> cinema_types = price_manager.GetCinemaTypeMultiplier();
				System.out.println("Cinema Type Prices:");
				for(Map.Entry<CinemaType, Float> entry: cinema_types.entrySet()){
					System.out.println(entry.getKey().name() + " : " + entry.getValue());
				}
			}
			else if(choice==7)
			{
				HashMap<ShowingEnum, Float> showing_types = price_manager.GetMovieTypePrice();
				System.out.println("Showing Type Prices:");
				for(Map.Entry<ShowingEnum, Float> entry: showing_types.entrySet()){
					System.out.println(entry.getKey().name() + " : " + entry.getValue());
				}			}
			else if(choice==8)
			{
				HashMap<AgeGroup, Float> age_groups = price_manager.GetAgeGroupMultiplier();
				System.out.println("Cinema Type Prices:");
				for(Map.Entry<AgeGroup, Float> entry: age_groups.entrySet()){
					System.out.println(entry.getKey().name() + " : " + entry.getValue());
				}
			}
			else if(choice==9)
			{
				HashMap<DayType, Float> day_types = price_manager.GetDayTypeMultiplier();
				System.out.println("Cinema Type Prices:");
				for(Map.Entry<DayType, Float> entry: day_types.entrySet()){
					System.out.println(entry.getKey().name() + " : " + entry.getValue());
				}
			}
			else if(choice == -1){
				System.out.println("Thank you for using price management service");
				return;
			}
			else
			{
				System.out.println("Invalid choice");
			}
		}
	}
	private void manageCineplexes()
	{
		System.out.println("Welcome to Cineplex Management Service");
		int choice;
		while(true)
		{
			System.out.println();
			System.out.println("Enter 1 to add cineplex");
			System.out.println("Enter 2 to remove cineplex");
			System.out.println("Enter 3 to add cinema");
			System.out.println("Enter 4 to remove cinema");
			System.out.println("Enter -1 to exit");
			System.out.println();
			System.out.print("Enter your choice... ");
			choice=sc.nextInt();

			if(choice==1)
			{
				System.out.println("Enter Cineplex name");
				String cineplex = sc.nextLine();
				if(cineplex_manager.AddCineplex(cineplex) != null)
					System.out.println("Cineplex added successfully");
				else
					System.out.println("Cinplex already exists");
				System.out.println();
			}
			else if(choice==2)
			{
				System.out.println("Enter Cineplex name");
				String cineplex = sc.nextLine();
				if(cineplex_manager.RemoveCineplex(cineplex))
					System.out.println("Cineplex removed successfully");
				else
					System.out.println("Cinplex not found");
				System.out.println();
			}
			else if(choice==3)
			{
				Cineplex cineplex = getCineplex();
				if(cineplex == null){
					continue;
				}
				System.out.println("Enter cinema name");
				String cinema=sc.nextLine();
				CinemaType type = getCinemaType();
				//TODO: Add Seating
				cineplex_manager.AddCinema(cineplex, cinema, type, null);
			}
			else if(choice==4)
			{
				Cineplex cineplex = getCineplex();
				if(cineplex == null){
					continue;
				}
				Cinema cinema = getCinema(cineplex);
				if(cinema == null){
					continue;
				}
				cineplex_manager.RemoveCinema(cineplex, cinema.getName());
			}else if(choice == -1){
				System.out.println("Thank you for using this service");
				return;
			}
			else
			{
				System.out.println("Invalid Choice");
			}
		}

				
	}
	
	public static void main(String[] sd)
	{
		AdminView ob=new AdminView();
		ob.optionsMenu();
	}
}
