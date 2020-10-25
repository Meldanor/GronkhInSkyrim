package de.meldanor.gronkskyrim.data;

import de.meldanor.gronkskyrim.events.EventType;

public class PlayerWeight implements EventData<PlayerWeight> {

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
