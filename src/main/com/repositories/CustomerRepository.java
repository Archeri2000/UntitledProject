package main.com.repositories;

import main.com.entities.Customer;
import main.com.utils.ISerialisable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

import static main.com.utils.SerialisationUtils.*;

/** This class acts as a repository for storing Customers
 * @author SS1 Group 6
 * @version 13
 */
public class CustomerRepository implements ISerialisable {
    /**
	 * Creation of a hashmap to store customers
	 */

    private HashMap<Integer, Customer> customerHashMap = new java.util.HashMap<>();

    /**
     * Get the customer details from the customerhashmap using phone number of the customer
     * @param phone - phone number of customer
     * @return customer object
     */
    public Customer getCustomer(int phone){
        if(customerHashMap.containsKey(phone)) return customerHashMap.get(phone);
        return null;
    }

     /**
     * Create a new Customer
     * @param name - name of the customer
     * @param email - email of the customer
     * @param phone - phone number of a customer
     * @return customer object
     */
    public Customer createCustomer(String name, String email, int phone){
        if(!customerHashMap.containsKey(name)){
            Customer c = new Customer(name, phone, email);
            customerHashMap.put(phone, c);
            return c;
        }
        return null;
    }
    /**
     * Creation of a static CustomerRepository object
     */
    private static CustomerRepository  _static_manager = new CustomerRepository();

    /**
     * Get the instance of CustomerRepository
     * @return CustomerRepository object
     */
    public static CustomerRepository getInstance(){
        if(_static_manager == null){
            _static_manager = new CustomerRepository();
        }
        return _static_manager;
    }

    /**
     * Serialize a String
     * @return serialized string
     */
    //TODO
    @Override
    public String toSerialisedString() {
        List<Customer> customers = new ArrayList<>(customerHashMap.values());
        return serialise(
                serialiseList(customers, "customers")
        );
    }

    /**
     * Deserialize a String
     * @param s - deserialize a string z
     * 
     */
    @Override
    public ISerialisable fromSerialisedString(String s) throws InvalidPropertiesFormatException {
        _static_manager = this;
        HashMap<String, String> pairs = deserialise(s);
        assert pairs != null;
        List<Customer> customers = deserialiseList(Customer.class, pairs.get("customers"));
        assert customers != null;
        for(Customer c:customers){
            customerHashMap.put(c.getMobileNumber(), c);
        }
        return getInstance();
    }
}
