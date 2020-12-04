package de.meldanor.gronkskyrim.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class RunningAverage {

    private static final NumberFormat PERCENTAGE = DecimalFormat.getPercentInstance();

    private double n = 0.0;
    private double curAverage = 0.0;

    public double add(double value) {
        curAverage = ((curAverage * n) + value) / ++n;
        return curAverage;
    }

    public double getCurAverage() {
        return curAverage;
    }

    public String getAsPercentage() {
        return PERCENTAGE.format(curAverage);
    }

    @Override
    public String toString() {
        return "RunningAverage{" +
                "n=" + n +
                ", curAverage=" + curAverage +
                '}';
    }
}
