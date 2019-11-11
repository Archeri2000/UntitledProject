package main.com.view;
import java.util.*;
import java.io.*;
public class Login {
	HashMap<String, String> user = new HashMap<String, String>();
	
	public Login()
	{
		user.put("12345","Login@123456");
	}
	public boolean addStaff(String user_id,String password)
	{
		boolean check_id=false;
		if(!(user.containsKey(user_id)))
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
		
		if(user.containsKey(id) && (user.get(id)).equals(password))
			return true;
		else
			return false;
			
	}
	
	public boolean changePassword(String userid,String password,String confirm_password)
	{
	boolean b=false;
		
				if(user.containsKey(userid) && password.equals(confirm_password))
				{
					user.remove(userid);
					user.put(userid,confirm_password);
					b=true;
				}
				else
					b=false;
		
		return b;
	}
	
	public boolean checkPassword(String password)
	{
		
		boolean b=false;
		int length_of_password=password.length();
		
		if(length_of_password<10)
			b=false;
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
				b=true;
			else
				b=false;
		}
		return b;
		
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


