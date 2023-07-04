import java.util.Arrays;
import java.util.Scanner;
public class Cinema {

    public static void main(String[] args) {
        // Ask user for # rows & # seats per row
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numSeatsPerRow = scanner.nextInt();

        // Initialize cinema
        String[][] cinema = new String[numRows][numSeatsPerRow];
        for (int i = 0; i < numRows; i++) {
            Arrays.fill(cinema[i], "S");
        }

        getUserInput(scanner, numRows, numSeatsPerRow, cinema);
    }

    private static void purchaseTicket(Scanner scanner, int numRows, int numSeatsPerRow, String[][] cinema) {
        // Get user's chosen seat
        System.out.println("\nEnter a row number:");
        int userRowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int userSeatNumber = scanner.nextInt();

        // Calculate ticket prices, book seat and print user's ticket price
        int totalSeats = numRows * numSeatsPerRow;
        int frontHalf = (numRows / 2);
        cinema[userRowNumber - 1][userSeatNumber - 1] = "B";
        int ticketPrice = getTicketPrice(userRowNumber, totalSeats, frontHalf);
    }

    public static void getUserInput(Scanner scanner, int numRows, int numSeatsPerRow, String[][] cinema) {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("0. Exit");

        int input = scanner.nextInt();
        switch (input) {
            case 0:
                break;
            case 1:
                printSeatingArrangement(numRows, numSeatsPerRow, cinema);
                getUserInput(scanner, numRows, numSeatsPerRow, cinema);
                break;
            case 2:
                purchaseTicket(scanner, numRows, numSeatsPerRow, cinema);
                getUserInput(scanner, numRows, numSeatsPerRow, cinema);
                break;
        }
    }

    private static int getTicketPrice(int userRowNumber, int totalSeats, int frontHalf) {
        int ticketPrice;
        if (totalSeats > 60) {
            if (userRowNumber <= frontHalf) {
                ticketPrice = 10;
                System.out.println("Ticket price: $" + ticketPrice);
            } else {
                ticketPrice = 8;
                System.out.println("Ticket price: $" + ticketPrice);
            }
        } else {
            ticketPrice = 10;
            System.out.println("Ticket price: $" + ticketPrice);
        }
        return ticketPrice;
    }

    private static void printSeatingArrangement(int numRows, int numSeatsPerRow, String[][] cinema) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= numSeatsPerRow; i++) {
            System.out.print(i + " ");  // print instead of println, so it's printed on same line
        }
        System.out.println();

        for (int i = 0; i < numRows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < numSeatsPerRow; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }


}

