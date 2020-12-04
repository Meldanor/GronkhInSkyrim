package de.meldanor.gronkskyrim.data;

import de.meldanor.gronkskyrim.events.EventType;
import de.meldanor.gronkskyrim.ocr.ParseException;
import de.meldanor.gronkskyrim.serialize.dto.EventDataDto;
import de.meldanor.gronkskyrim.serialize.dto.PlayerWeightDto;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerWeight implements EventData<PlayerWeight> {

    private final int currentWeight;
    private final int maximumWeight;

    private static final Pattern WEIGHT_PATTERN = Pattern.compile("Traglast\\s*(\\d+)\\s?/\\s?(\\d+)");

    public PlayerWeight(String ocrText) {
        Matcher matcher = WEIGHT_PATTERN.matcher(ocrText);
        if (matcher.find()) {
            this.currentWeight = Integer.parseInt(matcher.group(1).strip());
            this.maximumWeight = Integer.parseInt(matcher.group(2).strip());
        } else {
            throw new ParseException(ocrText);
        }
    }

    public PlayerWeight(int currentWeight, int maximumWeight) {
        this.currentWeight = currentWeight;
        this.maximumWeight = maximumWeight;
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

    @Override
    public EventType getEventType() {
        return EventType.PLAYER_WEIGHT;
    }

    @Override
    public EventDataDto toSerializable() {
        return new PlayerWeightDto(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerWeight that = (PlayerWeight) o;
        return currentWeight == that.currentWeight && maximumWeight == that.maximumWeight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentWeight, maximumWeight);
    }
}
