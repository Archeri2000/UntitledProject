package assignment;
import java.util.*;
public class AdminView 
{
	public static boolean login_status=false;
	Login admin=new Login();
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
		Scanner sc=new Scanner(System.in);
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
			else if(choice>4||choice<0)
			{
				System.out.println("Invalid choice");
			}
			
			System.out.println();
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
		
	}
	public static void main(String sd[])
	{
		AdminView ob=new AdminView();
		ob.optionsMenu();
	}
}
