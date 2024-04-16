package org.example;

import java.util.Scanner;


public class Playgame {

    private final Scanner scanner;

    public Playgame() {

        scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Welcome to Mastermind!");


        System.out.println("Please choose an option:");
        System.out.println("1. Make a guess");
        System.out.println("2. Quit game");
        System.out.print("Enter your choice (1, 2): ");

        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice. Please enter a valid option.");

        }


        switch (choice) {
            case 1:
                // Make a guess
                System.out.print("Enter your guess (4 different numbers from 0-7, separated by spaces): ");


                break;


            case 2:
                // Quit the game
                System.out.println("Thank you for playing! Goodbye.");
                return;

            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

}
