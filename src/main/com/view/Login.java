package main.com.view;
import main.com.utils.ISerialisable;

import java.util.*;
import java.io.*;
public class Login implements ISerialisable {
	HashMap<String, String> user = new HashMap<String, String>();
	
	public Login()
	{
		user.put("12345","Login@123456");
		_instance = this;
	}
	public boolean addStaff(String user_id,String password)
	{
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
		return user.containsKey(id) && user.get(id).equals(password);
			
	}
	
	public boolean changePassword(String userid,String password,String confirm_password)
	{
		if(user.containsKey(userid) && password.equals(confirm_password))
		{
			user.remove(userid);
			user.put(userid,confirm_password);
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public boolean checkPassword(String password)
	{
		
		int length_of_password=password.length();
		
		if(length_of_password < 10) return false;
		boolean check_special_character = false, check_capital = false, check_number = false;
		for(Character character:password.toCharArray())
		{
			if(Character.isDigit(character))
				check_number=true;
			if(Character.isUpperCase(character))
				check_capital=true;
			if((!Character.isLetter(character)) && !(Character.isDigit(character)))
				check_special_character=true;
		}
		return check_special_character && check_capital && check_number;
	}
	
	public List<String> getStaff()
	{
		return new ArrayList<>(user.keySet());
	}

	@Override
	public String toSerialisedString() {
		return null;
	}

	private static Login _instance;
	public static Login getInstance(){
		if(_instance == null){
			_instance = new Login();
		}
		return _instance;
	}
	@Override
	public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
		return null;
	}
}


