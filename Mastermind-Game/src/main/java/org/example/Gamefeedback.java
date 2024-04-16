package org.example;

public class Gamefeedback {
    final int correct;
    final int correctLocation;

    public Gamefeedback(int correct, int correctLocation) {
        this.correct = correct;
        this.correctLocation = correctLocation;
    }

    @Override
    public String toString() {
        return "Gamefeedback{" +
                "correct=" + correct +
                ", correctLocation=" + correctLocation +
                '}';
    }
}
