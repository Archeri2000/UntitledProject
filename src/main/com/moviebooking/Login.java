package main.com.moviebooking;
import java.util.*;
import java.io.*;
public class Login {
	HashMap<String, String> user = new HashMap<String, String>();
	
	public Login()
	{
		
	}
	public boolean addStaff(String user_id,String password)
	{
		boolean check_id=false;
		for(String ids : user.keySet())
		{
			if(ids.equals(user_id))
				check_id=true;
		}
		if(check_id==false)
		{
			user.put(user_id,password);
			return true;
		}
		else
			return false;
	}
	
	public boolean checkLogin(String id,String password)
	{
		boolean check_id=false,check_password=false;
		for(String ids : user.keySet())
		{
			if(ids.equals(id))
				check_id=true;
		}
		for(String passwords : user.values())
		{
			if(passwords.equals(password))
				check_password=true;
		}
		if(check_id && check_password)
			return true;
		else
			return false;
			
	}
	
	public String changePassword(String userid,String new_password,String confirm_password)
	{
		String message="";
		
		if(!(new_password.equals(confirm_password)))
			message="passwords do not match";
		else
		{
				
			for(String userids : user.keySet())
			{	
			
				if(userids.equals(userid))
				{
					user.remove(userids);
					user.put(userids,new_password);
					message= "Password updated successfully";
				}
					
			}
			
		if(message.equals(""))
				message= "incorrect userid";
		}
		
		return message;
		
		
	}
	
	public String checkPassword(String password)
	{
		String message="";
		int length_of_password=password.length();
		
		if(length_of_password<10)
			message="password should have atleast 10 characters";
		else
		{
			int i;
			char character;
			boolean check_special_character=false,check_capital=false,check_number=false;
			for(i=0;i<length_of_password;i++)
			{
				character=password.charAt(i);
				if(Character.isDigit(character))
					check_number=true;
				if(Character.isUpperCase(character))
					check_capital=true;
				if((!Character.isLetter(character)) && !(Character.isDigit(character)))
					check_special_character=true;
			}
			if(check_special_character && check_capital && check_number)
				message="Strong password";
			else
				message="password must contain a number,capital alphabet and a special character";
		}
		return message;
		
	}
	
	public void displayStaff()
	{
		
		 Set set = user.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) 
	      {
	         Map.Entry mentry = (Map.Entry)iterator.next();
	         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	         System.out.println(mentry.getValue());
	      }
	
	
	}
}


