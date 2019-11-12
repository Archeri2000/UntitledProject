package assignment;
import java.util.*;
public class AdminView 
{
	public static boolean login_status=false;
	
	Login admin=new Login();
	
	MovieManagementService movie_manager=new MovieManagementService();
	ShowingManagementService show_manager=new ShowingManagementService();
	PriceManagementService price_manager=new PriceManagementService();
	CineplexManagementService cineplex_manager=new CineplexManagementService();
	
	
	
	Scanner sc=new Scanner(System.in);
	private void entry()
	{
		System.out.println();
	}
	private boolean login(String user_id,String password)
	{
		boolean b=true;
		
		b=admin.checkLogin(user_id,password);
		return b;
	}
	private void optionsMenu()
	{
		
		System.out.println("Welcome to the Admin panel");
		System.out.println();
		System.out.println("Enter -1 to exit");
		System.out.println("Enter 0 to view all staff");
		System.out.println("Enter 1 to Login");
		System.out.println("Enter 2 to change password");
		System.out.println("Enter 3 to add staff");
		System.out.println("Enter 4 to log out");
		System.out.print("Enter your choice......  ");
		int choice=sc.nextInt();
		
		while(choice!=-1)
		{
			
			if(choice== 0)
			{
				if(login_status)
					admin.displayStaff();
				else
					System.out.println("Please login before using this option");
			}
			if(choice== 1)
			{
				System.out.print("Enter user id... ");
				String user_id=sc.next();
				System.out.println();
				System.out.print("Enter password...");
				String password=sc.next();
				boolean b=login(user_id,password);
				
				if(b==true)
				{
					System.out.println("Logged in successfully");
					login_status=true;
				}
				else
					System.out.println("Incorrect login id or password");
				
				
			}
			if(choice== 2)
			{
				if(login_status)
				{
				System.out.print("Enter user id... ");
				String user_id=sc.next();
				System.out.println();
				System.out.print("Enter new password...");
				String password=sc.next();
				System.out.print("Confirm password...");
				String new_password=sc.next();
				boolean check_new_password=admin.checkPassword(new_password);
				while(!check_new_password)
				{
					if(!check_new_password)
						System.out.println("Password not strong enough please enter again");
					new_password=sc.next();
					check_new_password=admin.checkPassword(new_password);
				}
				boolean b=admin.changePassword(user_id,password,new_password);
				if(b==true)
					System.out.println("Password changed succesfully");
				else
					System.out.println("Password change unsuccesfull");
				}
				else
					System.out.println("Please login before using this option");
			}
			
			if(choice== 3)
			{
				if(!login_status)
					System.out.println("Please login before using this option");
				else
				{
					System.out.print("Enter user id... ");
					String user_id=sc.next();
					System.out.println();
					System.out.print("Enter password...");
					String password=sc.next();
					boolean check_new_password=admin.checkPassword(password);
					while(!check_new_password)
					{
						if(!check_new_password)
							System.out.println("Password not strong enough please enter again");
						password=sc.next();
						check_new_password=admin.checkPassword(password);
					}
					boolean check_addstaff=admin.addStaff(user_id,password);
					if(!check_addstaff)
						System.out.println("User already exists");
					else
						System.out.println("Staff added successfully");
				}
			}
			if(choice==4)
			{
				login_status=false;
				System.out.println("Logged out successfully");
			}
			if(choice==5)
				manageMovies();
			if(choice==6)
				manageShowtimes();
			if(choice==7)
				managePrice();
			if(choice==8)
				manageCineplexes();
			else
			{
				System.out.println("Invalid choice");
			}
			
			
			System.out.println();
			System.out.println("Enter -1 to exit");
			System.out.println("Enter 0 to view all staff");
			System.out.println("Enter 1 to Login");
			System.out.println("Enter 2 to change password");
			System.out.println("Enter 3 to add staff");
			System.out.println("Enter 4 to log out");
			if(login_status)
			{
				System.out.println("Enter 5 to manage movies");
				System.out.println("Enter 6 to manage show times");
				System.out.println("Enter 7 to manage price of movies");
				System.out.println("Enter 8 to manage cineplexes");
			}
			System.out.println();
			System.out.print("Enter your choice......  ");
			choice=sc.nextInt();
		}
		if(choice==-1)
			System.out.println("Thanyou for your visit");
		
	}
	private void manageMovies()
	{
		System.out.println("Welcome to Movie Management Service");
		System.out.println();
	
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
		int choice=sc.nextInt();
		while(choice!=-1)
		{
			if(choice==1)
			{
				System.out.print("Enter movie title...");
				String title=sc.nextLine();
				System.out.println();
				System.out.print("Enter movie duration...");
				int duration=sc.nextInt();
				System.out.println();
				System.out.print("Enter movie rating certification...");
				String rating=sc.next();
				System.out.println();
				System.out.print("Enter movie status...");
				String status=sc.next();
				System.out.println();
				System.out.print("Enter movie synopsis...");
				String synopsis=sc.nextLine();
				System.out.println();
				System.out.print("Enter movie director...");
				String director=sc.nextLine();
				System.out.println();
				System.out.println("Enter movie cast and enter -99 to stop");
				String cast=sc.nextLine();
				ArrayList <String> cast_members=new ArrayList();
				while(!cast.equals(-1))
				{
					cast_members.add(cast);
					System.out.println("Enter movie cast and enter -99 to stop");
					cast=sc.nextLine();
				}
				Reviews review=new Reviews();
				movie_manager.addMovie(title,duration,rating,status,synopsis,director,cast_members,review);
			}
			if(choice==2)
			{
				System.out.print("Enter movie title...");
				String movie_title=sc.nextLine();
				System.out.println();
				System.out.print("Enter movie status");
				String status=sc.next();
				movie_manager.setMovieStatus(movie_title,status);
			}
			if(choice==3)
			{
				System.out.print("Enter movie title...");
				String movie_title=sc.nextLine();
				System.out.println();
				System.out.print("Enter movie certification");
				String rating=sc.next();
				movie_manager.setMovieRating(movie_title,rating);
			}
			if(choice==4)
			{
				System.out.print("Enter movie title...");
				String movie_title=sc.nextLine();
				System.out.println();
				movie_manager.removeMovie(movie_title);
			}
			if(choice==5)
			{
				System.out.print("Enter movie title...");
				String movie_title=sc.nextLine();
				System.out.println();
				System.out.print("Enter movie synopsis");
				String synopsis=sc.nextLine();
				movie_manager.setMovieSynopsis(movie_title,synopsis);
			}
			if(choice==6)
			{
				System.out.print("Enter movie title...");
				String movie_title=sc.nextLine();
				System.out.println();
				System.out.print("Enter movie director");
				String director=sc.nextLine();
				movie_manager.setMovieRating(movie_title,director);
			}
			if(choice==7)
			{
				System.out.print("Enter movie title...");
				String movie_title=sc.nextLine();
				System.out.println();
				System.out.print("Enter movie cast");
				String cast=sc.nextLine();
				movie_manager.addMovieCast(movie_title,cast);
			}
			if(choice==8)
			{
				System.out.print("Enter movie title...");
				String movie_title=sc.nextLine();
				System.out.println();
				System.out.print("Enter movie cast");
				String cast=sc.nextLine();
				movie_manager.removeMovieCast(movie_title,cast);
			}
			if(choice==9)
			{
				System.out.print("Enter movie title...");
				String movie_title=sc.nextLine();
				movie_manager.getMovie(movie_title);
			}
			else
			{
				System.out.println("Invalid choice");
			}
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
			}
			if(choice==2)
			{
				System.out.println("Enter Cineplex name");
				String cineplex=sc.nextLine();
				// call RemoveCineplex method from CineplexManagementService class
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
	
	public static void main(String sd[])
	{
		AdminView ob=new AdminView();
		ob.optionsMenu();
	}
}