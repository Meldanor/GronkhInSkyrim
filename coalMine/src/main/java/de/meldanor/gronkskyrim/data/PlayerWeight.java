package de.meldanor.gronkskyrim.data;

import de.meldanor.gronkskyrim.events.EventType;
import de.meldanor.gronkskyrim.ocr.ParseException;

import java.util.Optional;
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

    private PlayerWeight(int currentWeight, int maximumWeight) {
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
    public String toEventLogString() {
        return toString();
    }

    private static final Pattern EVENT_LOG_PATTERN = Pattern.compile("PLAYER_WEIGHT=(\\d+)/(\\d+)");

    public static Optional<PlayerWeight> fromEventLogString(String eventLogString) {
        Matcher matcher = EVENT_LOG_PATTERN.matcher(eventLogString);
        if (matcher.find()) {
            int currentWeight = Integer.parseInt(matcher.group(1));
            int maximumWeight = Integer.parseInt(matcher.group(2));
            return Optional.of(new PlayerWeight(currentWeight, maximumWeight));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public EventType getEventType() {
        return EventType.PLAYER_WEIGHT;
    }
}
