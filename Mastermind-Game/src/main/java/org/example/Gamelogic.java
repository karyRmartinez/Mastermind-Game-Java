package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gamelogic {
    public static final int CODE_LENGTH = 4;

    private static final int MAX_ATTEMPTS = 10;
    private int attemptsLeft;
    private final List<GuessEntry> historyAttempts;
    private final List<Integer> secretCode;


    public Gamelogic() {
        secretCode = NumbersAPI.generateSecretCode(CODE_LENGTH);
        historyAttempts = new ArrayList<>();
        attemptsLeft = MAX_ATTEMPTS;
    }

    public Gamefeedback checkGuess(List<Integer> guess) {
        int correctNumbers = 0; // Number of correct numbers
        int correctLocations = 0; // Number of correct numbers in the correct location

        // Arrays to track which elements have been matched
        boolean[] guessMatched = new boolean[CODE_LENGTH];
        boolean[] secretMatched = new boolean[CODE_LENGTH];

        // First : Check for correct locations
        for (int i = 0; i < CODE_LENGTH; i++) {
            if (guess.get(i).equals(secretCode.get(i))) {
                correctLocations++;
                correctNumbers++;
                // Mark these positions as matched
                guessMatched[i] = true;
                secretMatched[i] = true;
            }
        }

        // Second : Check for correct numbers
        for (int i = 0; i < CODE_LENGTH; i++) {
            // Only consider unmatched guess elements
            if (!guessMatched[i]) {
                for (int j = 0; j < CODE_LENGTH; j++) {
                    if (!secretMatched[j] && guess.get(i).equals(secretCode.get(j))) {
                        correctNumbers++;
                        secretMatched[j] = true;
                        break; // Exit the loop once a match is found
                    }
                }
            }
        }

        return new Gamefeedback(correctNumbers, correctLocations);
    }






    public void recordGuess(List<Integer> guess, Gamefeedback feedback) {
        historyAttempts.add(new GuessEntry(guess, feedback));
        attemptsLeft--;
    }

    public boolean hasAttemptsLeft() {
        return attemptsLeft > 0;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public List<Integer> getSecretCode() {
        return secretCode;
    }

    // Inner class to represent a guess entry
    private static class GuessEntry {
        final List<Integer> guess;
        final Gamefeedback feedback;

        GuessEntry(List<Integer> guess, Gamefeedback feedback) {
            this.guess = guess;
            this.feedback = feedback;
        }
    }





    public class GameUtils {
        public static List<Integer> readGuess(Scanner scanner) {
            String input = scanner.nextLine();
            String[] parts = input.trim().split("\\s+");

            if (parts.length != Gamelogic.CODE_LENGTH) {
                System.out.println("Invalid input. Please enter " + Gamelogic.CODE_LENGTH + " numbers.");
                return null;
            }

            List<Integer> guess = new ArrayList<>();
            for (String part : parts) {
                try {
                    int num = Integer.parseInt(part);
                    if (num < 0 || num > 7) {
                        System.out.println("Invalid input. Numbers should be between 0 and 7.");
                        return null;
                    }
                    guess.add(num);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter valid integers.");
                    return null;
                }
            }

            return guess;
        }
    }
}
