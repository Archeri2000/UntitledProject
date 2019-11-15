package main.com.repositories;

import main.com.entities.Customer;
import java.util.HashMap;

public class CustomerRepository {

    private HashMap<Integer, Customer> cineplexHashMap = new java.util.HashMap<>();

    public Customer getCustomer(int phone){
        if(cineplexHashMap.containsKey(phone)) return cineplexHashMap.get(phone);
        return null;
    }

    public Customer createCustomer(String name, String email, int phone){
        if(!cineplexHashMap.containsKey(name)){
            Customer c = new Customer(name, phone, email);
            return c;
        }
        return null;
    }

    private static CustomerRepository  _static_manager = new CustomerRepository();

    public static CustomerRepository getInstance(){
        if(_static_manager == null){
            _static_manager = new CustomerRepository();
        }
        return _static_manager;
    }
}
