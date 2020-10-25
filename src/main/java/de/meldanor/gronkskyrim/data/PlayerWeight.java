package de.meldanor.gronkskyrim.data;

public class PlayerWeight {

    private final int currentWeight;
    private final int maximumWeight;

    public PlayerWeight(String ocrText) {
        String[] split = ocrText.strip().split("/");
        this.currentWeight = Integer.parseInt(split[0].strip());
        this.maximumWeight = Integer.parseInt(split[1].strip());
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getMaximumWeight() {
        return maximumWeight;
    }


    @Override
    public String toString() {
        return currentWeight + "/" + maximumWeight;
    }
}
