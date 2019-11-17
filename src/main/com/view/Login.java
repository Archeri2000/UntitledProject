package main.com.view;
import main.com.utils.ISerialisable;

import java.util.*;
import java.io.*;

/** Login class is acts as a backend service for the login portal of admin panel
 * @author SS1 Group 6
 * @version 13
 */
public class Login implements ISerialisable {
	
	/**
	 * Hashmap user is used to store user_id and password of the various admins
	 */
	
	HashMap<String, String> user = new HashMap<String, String>();
	
	/**
	 * Login constructor to store the id and password of the initial admin
	 */
	
	public Login()
	{
		user.put("12345","Login@123456");
		_instance = this;
	}
	
	/**
	 * Adds the user_id and password of a new admin member in the user hashmap
	 * @param user_id - used to store the user_id of the admin
	 * @param password - used to store the password of the admin
	 * @return - returns a boolean value to check if the user_id already exists or not
	 */
	
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
	/**
	 * Checks user_id and password in the hashmap
	 * @param id - userid of admin
	 * @param password - password of admin
	 * @return
	 */
	
	public boolean checkLogin(String id,String password)
	{
		return user.containsKey(id) && user.get(id).equals(password);
			
	}
	/**
	 * Change the password of an admin
	 * @param userid - user_id of the admin
	 * @param password - password of the admin
	 * @param confirm_password - confirmed password entered by the admin
	 * @return a boolean value to verify change in password
	 */
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
	/**
	 * check if the password is strong enough
	 * @param password - password entered by the admin to check
	 * @return a boolean value to state if the entered password is strong enough or not
	 */
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
	
	/**
	 * Adds a staff member in the user hashmap
	 * @return
	 */
	
	public List<String> getStaff()
	{
		return new ArrayList<>(user.keySet());
	}

	
	/**
	 * Serialization
	 */
	@Override
	public String toSerialisedString() {
		return null;
	}
	
	/**
	 * Get an instance of login clas object
	 * @return _instance object
	 */

	private static Login _instance;
	public static Login getInstance(){
		if(_instance == null){
			_instance = new Login();
		}
		return _instance;
	}
	/**
	 * Deserialization
	 */
	@Override
	public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
		return null;
	}
}


