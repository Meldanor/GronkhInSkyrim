package de.meldanor.gronkskyrim.util;

public class RunningAverage {

    private double n = 0.0;
    private double curAverage = 0.0;

    public double add(double value) {
        curAverage = ((curAverage * n) + value) / ++n;
        return curAverage;
    }

    public double getCurAverage() {
        return curAverage;
    }

    @Override
    public String toString() {
        return "RunningAverage{" +
                "n=" + n +
                ", curAverage=" + curAverage +
                '}';
    }
}
