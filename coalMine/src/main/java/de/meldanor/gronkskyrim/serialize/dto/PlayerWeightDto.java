package de.meldanor.gronkskyrim.serialize.dto;

import de.meldanor.gronkskyrim.data.PlayerWeight;

public class PlayerWeightDto extends EventDataDto {
    private int current;
    private int maximum;

    public PlayerWeightDto() {
    }

    public PlayerWeightDto(PlayerWeight original) {
        this.current = original.getCurrentWeight();
        this.maximum = original.getMaximumWeight();
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public PlayerWeight toOriginal() {
        return new PlayerWeight(this.current, this.maximum);
    }
}
