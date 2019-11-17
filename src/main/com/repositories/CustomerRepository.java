package main.com.repositories;

import main.com.entities.Customer;
import main.com.utils.ISerialisable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

import static main.com.utils.SerialisationUtils.*;

public class CustomerRepository implements ISerialisable {

    private HashMap<Integer, Customer> customerHashMap = new java.util.HashMap<>();

    public Customer getCustomer(int phone){
        if(customerHashMap.containsKey(phone)) return customerHashMap.get(phone);
        return null;
    }

    public Customer createCustomer(String name, String email, int phone){
        if(!customerHashMap.containsKey(name)){
            Customer c = new Customer(name, phone, email);
            customerHashMap.put(phone, c);
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

    //TODO
    @Override
    public String toSerialisedString() {
        List<Customer> customers = new ArrayList<>(customerHashMap.values());
        return serialise(
                serialiseList(customers, "customers")
        );
    }

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
