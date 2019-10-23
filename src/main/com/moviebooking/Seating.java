package main.com.moviebooking;


import main.com.serialisation.ISerialisable;

import java.util.Scanner;

public class Seating implements ISerialisable
{

    public Seating fromSerialisedString(String s){
        return null;
    }

    public String toSerialisedString(){
        return null;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here

        Scanner input = new Scanner(System.in);        
        boolean chooseSeats=true;
        String stopInput;
        int methodChoice;
        
        int[][] seatMap = { {10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                            {10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                            {10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                            {10, 10, 20, 20, 20, 20, 20, 20, 10, 10}, 
                            {10, 10, 20, 20, 20, 20, 20, 20, 10, 10},
                            {20, 20, 30, 30, 40, 40, 30, 30, 20, 20}, 
                            {20, 30, 30, 40, 50, 50, 40, 30, 30, 20},
                            {30, 40, 50, 50, 50, 50, 50, 50, 40, 30} };

        while(chooseSeats)
        {
            System.out.println("Welcome to Theater Alexander.");
            System.out.println("Press 1 to choose a seat by price.");
            System.out.println("Press 2 to choose a seat by number.");
            System.out.print("Enter: ");            
            methodChoice = input.nextInt();
            System.out.println();

            switch(methodChoice)
            {
                case 1: seatByPrice(seatMap); break;
                case 2: printSeatMap(seatMap); seatByCoord(seatMap); break;
                default: System.out.println("\nInvalid choice, try again.\n");
            }          
                        
            System.out.print("Enter Q to quit, any other input to purchase a new seat: ");
            stopInput = input.next();
            System.out.println();
            
            if(stopInput.matches("Q"))
            {chooseSeats=false;}
            
        }//END OF WHILE LOOP TO CHOOSE SEATS

    }//end of main
    
    public static void printSeatMap(int[][] seatMap)
    {//generic print method
        int row, column;
        
        System.out.println("Seats are marked by their ticket price.");
        System.out.println("Seats marked with a 00 are unavailable.\n");
        
        System.out.println("   01 02 03 04 05 06 07 08 09 10");//print column numbers
        System.out.println("   -----------------------------");//print column numbers
        
        for(row=0; row<8; row++)
        {//outer print loop
            System.out.print(String.format("%02d",row+1));
            System.out.print("|");//print row numbers
            
            for(column=0; column<10; column++)
            {//inner print loop
                    System.out.print(String.format("%02d",seatMap[row][column]));
                System.out.print(" ");
                
            }//inner print loop     
            
            System.out.println();
            
        }//outer print loop
        
    }//end generic print method
    
    public static void priceSelectMap(int[][] seatMap, int userSelection) 
    {//print method, used by seatByPrice method
        int row, column;        
       
        System.out.println("   01 02 03 04 05 06 07 08 09 10");//print column numbers
        System.out.println("   -----------------------------");//print column numbers
        
        for(row=0; row<8; row++)
        {//outer print loop
            System.out.print(String.format("%02d",row+1));
            System.out.print("|");//print row numbers
            
            for(column=0; column<10; column++)
            {//inner print loop
                
                if(seatMap[row][column] == userSelection)
                {
                    System.out.print(String.format("%02d",seatMap[row][column]));
                    System.out.print(" ");                    
                }
                
                else
                {
                    System.out.print("   ");
                }
            }//inner print loop     
            
            System.out.println();
            
        }//outer print loop

    }//end print method, used by seatByPrice method
    
    public static void seatByPrice(int[][] seatMap)
    {
        Scanner userIn = new Scanner(System.in);
        int chosenPrice;
        
        System.out.print("Please enter a price (10, 20, 30, 40, 50): ");
        chosenPrice = userIn.nextInt();
        System.out.println();
        
        while(chosenPrice != 10 && chosenPrice != 20 && chosenPrice != 30 && chosenPrice != 40 && chosenPrice !=50)
        {
            System.out.println("Invalid choice.");
            System.out.print("Please enter a price (10, 20, 30, 40, 50): ");
            chosenPrice = userIn.nextInt();
            System.out.println();
        }
        
        priceSelectMap(seatMap, chosenPrice);
        
        seatByCoord(seatMap);
    }//end of seat by price
    
    public static void seatByCoord(int[][] seatMap)
    {
        Scanner userIn = new Scanner(System.in);
        int userRow, userCol;
        
        System.out.println("\nPlease enter seat coordinates.");
        System.out.print("Row: ");
        userRow = userIn.nextInt();
        System.out.print("Column: ");
        userCol = userIn.nextInt();

        while( userRow < 1 || userRow > 8 || userCol < 1 || userCol > 10 || seatMap[userRow-1][userCol-1]==0)
        {//bad input
            System.out.println("Invalid input, try again.");
            System.out.println("\nPlease enter seat coordinates.");
            System.out.print("Row: ");
            userRow = userIn.nextInt();
            System.out.print("Column: ");
            userCol = userIn.nextInt();
        }
        
        System.out.println("\n\nThank you for your choice.");
        System.out.print("Your account has been charged $");
        System.out.print(seatMap[userRow-1][userCol-1]);
        System.out.println(" and an additional $150 in service charges.");
        seatMap[userRow-1][userCol-1]=0;
        
    }//end of seat by price

    public Seating getNewSeatingPlan() {
        return null;
    }
}