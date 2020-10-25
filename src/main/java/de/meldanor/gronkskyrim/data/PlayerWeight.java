package de.meldanor.gronkskyrim.data;

import de.meldanor.gronkskyrim.events.EventType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerWeight implements EventData<PlayerWeight> {

    private final int currentWeight;
    private final int maximumWeight;

    private static final Pattern WEIGHT_PATTERN = Pattern.compile("Traglast\\s+(\\d+)/(\\d+)");

    public PlayerWeight(String ocrText) {
        Matcher matcher = WEIGHT_PATTERN.matcher(ocrText);
        if (matcher.find()) {
            this.currentWeight = Integer.parseInt(matcher.group(1).strip());
            this.maximumWeight = Integer.parseInt(matcher.group(2).strip());
        } else {
            throw new RuntimeException("Can't parse '" + ocrText + "'");
        }
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

    @Override
    public PlayerWeight fromEventLogString(String eventLogString) {
        return new PlayerWeight(eventLogString);
    }

    @Override
    public EventType getEventType() {
        return EventType.PLAYER_WEIGHT;
    }
}
