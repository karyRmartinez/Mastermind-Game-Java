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
        int correctNumbers = 0;
        int correctLocations = 0;

        List<Integer> secretCopy = new ArrayList<>(secretCode);
        List<Integer> guessCopy = new ArrayList<>(guess);

        //  correct locations first
        for (int i = 0; i < CODE_LENGTH; i++) {
            if (guessCopy.get(i).equals(secretCopy.get(i))) {
                correctLocations++;
                secretCopy.set(i, -1);
                guessCopy.set(i, -1);
            }
        }

        //  numbers in wrong locations
        for (int i = 0; i < CODE_LENGTH; i++) {
            int guessedNum = guessCopy.get(i);
            if (guessedNum != -1 && secretCopy.contains(guessedNum)) {
                correctNumbers++;
                secretCopy.set(secretCopy.indexOf(guessedNum), -1);
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
                return null;
            }

            List<Integer> guess = new ArrayList<>();
            for (String part : parts) {
                try {
                    int num = Integer.parseInt(part);
                    if (num < 0 || num >= Gamelogic.MAX_ATTEMPTS || guess.contains(num)) {
                        return null;
                    }
                    guess.add(num);
                } catch (NumberFormatException e) {
                    return null;
                }
            }

            return guess;
        }
    }
}
