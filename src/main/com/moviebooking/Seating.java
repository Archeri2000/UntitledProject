package main.com.moviebooking;

import main.com.utils.ISerialisable;

import java.util.Scanner;

public int[][] seatMap = { {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0} };

public class Seating implements ISerialisable {

    public Seating fromSerialisedString(String s) {
        return null;
    }

    public String toSerialisedString() {
        return null;
    }

}

public boolean isSeatEmpty(String seat)
{
    Scanner input = new Scanner(System.in);
    int userCol, userRow;

    System.out.println("\nPlease enter seat coordinates.");
    System.out.print("Row: ");
    userRow = userIn.nextInt();
    System.out.print("Column: ");
    userCol = userIn.nextInt();

    if ( userRow < 1 || userRow > 8 || userCol < 1 || userCol > 10 || seatMap[userRow-1][userCol-1]==0)
    {//bad input
        System.out.println("Invalid input, try again.");
        System.out.println("\nPlease enter seat coordinates.");
        System.out.print("Row: ");
        userRow = userIn.nextInt();
        System.out.print("Column: ");
        userCol = userIn.nextInt();
        return false;
    }
    else {
        System.out.println("\n\nThat seat is empty");
        return true;
    }


}



public boolean seatSeatsOccupied(String[] seats) {
    Scanner input = new Scanner(System.in);
    boolean chooseSeats = true;
    String stopInput;
    int methodChoice;


    while (chooseSeats) {
        System.out.println("Please select your seats");
        System.out.print("Enter: ");
        methodChoice = input.nextInt();
        System.out.println();
        printSeatMap(seatMap);
        seatByCoord(seatMap);

        System.out.print("Enter Q to quit, any other input to purchase a new seat: ");
        stopInput = input.next();
        System.out.println();

        if (stopInput.matches("Q")) {
            chooseSeats = false;
        }
    }
}

public static void getDisplayString(int[][] seatMap)
{//generic print method
    int row, column;

    System.out.println("Seats are marked by their ticket price.");
    System.out.println("Seats marked with a 0 are unavailable.\n");

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

public static void seatByCoord(int[][] seatMap)
{
    Scanner userIn = new Scanner(System.in);
    int userRow, userCol;

    System.out.println("\nPlease enter seat coordinates.");
    System.out.print("Row: ");
    userRow = userIn.nextInt();
    System.out.print("Column: ");
    userCol = userIn.nextInt();

    while( userRow < 1 || userRow > 8 || userCol < 1 || userCol > 10 || seatMap[userRow-1][userCol-1]==1)
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
    seatMap[userRow-1][userCol-1]=1;

}//end of seat by coord

public Seating getNewSeatingPlan() {
    return null;
}