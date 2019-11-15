package main.com.view;

import main.com.entities.Customer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int user = 0;
        do {

            System.out.println("Are you a Customer or Admin??\n" +
                    "1. Customer\n" +
                    "2. Admin");
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
                default:
                    System.out.println("Invalid Input! Try again");
            }
        }while (user > 2);
    }
}
