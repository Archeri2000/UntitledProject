package main.com.view;

import main.com.entities.Customer;
import main.com.repositories.CineplexRepository;
import main.com.repositories.CustomerRepository;
import main.com.repositories.MovieRepository;
import main.com.repositories.PriceRepository;
import main.com.utils.SerialisationUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        deserialiseManagers();
        int user = 0;
        do {
            System.out.println("Enter an option:\n" +
                    "1. Customer interface\n" +
                    "2. Admin interface\n" +
                    "3. Exit Program");
            Scanner sc = new Scanner(System.in);
            user = sc.nextInt();
            switch (user) {
                case 1:
                    CustomerUI customerUI = new CustomerUI();
                    customerUI.hi();
                    break;
                case 2:
                    AdminView adminView = new AdminView();
                    adminView.optionsMenu();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid Input! Try again");
            }
        }while (user != 3);
        System.out.println("Saving changes...");
        serialiseManagers();
        System.out.println("Thank you for using the system!");
    }

    private static void deserialiseManagers(){
        try {
            String movies = new Scanner(new File("movies.txt")).useDelimiter("\\Z").next();
            SerialisationUtils.deserialiseObject(MovieRepository.class, SerialisationUtils.deserialise(movies).get("movie_repo"));
            String prices = new Scanner(new File("prices.txt")).useDelimiter("\\Z").next();
            SerialisationUtils.deserialiseObject(PriceRepository.class, SerialisationUtils.deserialise(prices).get("price_repo"));
            String cineplexes = new Scanner(new File("cineplexes.txt")).useDelimiter("\\Z").next();
            SerialisationUtils.deserialiseObject(CineplexRepository.class, SerialisationUtils.deserialise(cineplexes).get("cineplex_repo"));
            String customers = new Scanner(new File("customers.txt")).useDelimiter("\\Z").next();
            SerialisationUtils.deserialiseObject(CustomerRepository.class, SerialisationUtils.deserialise(customers).get("customer_repo"));

        }catch(FileNotFoundException e){
            System.out.println("No existing files found!");
        } catch (IOException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void serialiseManagers(){
        String movies = SerialisationUtils.serialise(SerialisationUtils.serialiseObject(MovieRepository.getInstance(), "movie_repo"));
        saveToFile("movies.txt", movies);
        String prices = SerialisationUtils.serialise(SerialisationUtils.serialiseObject(PriceRepository.getInstance(), "price_repo"));
        saveToFile("prices.txt", prices);
        String cineplexes = SerialisationUtils.serialise(SerialisationUtils.serialiseObject(CineplexRepository.getInstance(), "cineplex_repo"));
        saveToFile("cineplexes.txt", cineplexes);
        String customers = SerialisationUtils.serialise(SerialisationUtils.serialiseObject(CustomerRepository.getInstance(), "customer_repo"));
        saveToFile("customers.txt", customers);
    }

    private static void saveToFile(String filepath, String contents){
        File file = new File(filepath);
        try {
            file.createNewFile();
        } catch (IOException e1) {
            System.out.println("IO Exception");
        }
        try(PrintWriter out = new PrintWriter(filepath)){
            out.print(contents);
        }catch(FileNotFoundException e){
            System.out.println("Unable to save " + filepath);
        }
    }
}
