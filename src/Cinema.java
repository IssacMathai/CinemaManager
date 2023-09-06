import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Cinema {
    private static int numPurchasedTickets;
    private static int totalSeats;
    private static int currentIncome;
    private static int totalIncome;


    public static void main(String[] args) {
        // Ask user for # rows & # seats per row
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numSeatsPerRow = scanner.nextInt();

        totalSeats = numRows * numSeatsPerRow;

        // Initialize cinema
        String[][] cinema = new String[numRows][numSeatsPerRow];
        for (int i = 0; i < numRows; i++) {
            Arrays.fill(cinema[i], "S");
        }

        getUserInput(scanner, numRows, numSeatsPerRow, cinema);
    }

    private static void purchaseTicket(Scanner scanner, int numRows, int numSeatsPerRow, String[][] cinema) {
        // Get user's chosen seat
        boolean valid = false;
        boolean outOfBounds = true;
        while (!valid && outOfBounds) {
            System.out.println("\nEnter a row number:");
            int userRowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int userSeatNumber = scanner.nextInt();

            if (userRowNumber <= numRows && userRowNumber > 0 && userSeatNumber <= numSeatsPerRow && userSeatNumber > 0) {
                if (Objects.equals(cinema[userRowNumber - 1][userSeatNumber - 1], "S")) {
                    // Calculate ticket prices, book seat and print user's ticket price
                    int totalSeats = numRows * numSeatsPerRow;
                    int frontHalf = (numRows / 2);
                    cinema[userRowNumber - 1][userSeatNumber - 1] = "B";
                    int ticketPrice = getTicketPrice(userRowNumber, totalSeats, frontHalf, numSeatsPerRow, numRows);
                    currentIncome += ticketPrice;
                    valid = true;
                    outOfBounds = false;
                } else {
                    System.out.println("That ticket has already been purchased!");
                }
            } else {
                System.out.println("Wrong Input!");
            }
        }
    }


    public static void getUserInput(Scanner scanner, int numRows, int numSeatsPerRow, String[][] cinema) {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
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
                numPurchasedTickets += 1;
                getUserInput(scanner, numRows, numSeatsPerRow, cinema);
                break;
            case 3:
                printStatistics(numSeatsPerRow, numRows);
                getUserInput(scanner, numRows, numSeatsPerRow, cinema);

        }
    }

    private static void printStatistics(int numSeatsPerRow, int numRows) {
        if (totalSeats > 60) {
            int frontHalf = (numRows / 2);
            totalIncome = (frontHalf * numSeatsPerRow * 10) + ((numRows - frontHalf) * numSeatsPerRow * 8);
        } else {
            totalIncome = totalSeats * 10;
        }

        System.out.printf("Number of purchased tickets: %d \n", numPurchasedTickets);
        float percentage = ((float) numPurchasedTickets /totalSeats) * 100;
        System.out.printf("Percentage: %.2f%s\n", percentage, "%");
        System.out.printf("Current income: $%d \n", currentIncome);
        System.out.printf("Total potential income: $%d \n", totalIncome);
    }

    private static int getTicketPrice(int userRowNumber, int totalSeats, int frontHalf, int numSeatsPerRow, int numRows) {
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

