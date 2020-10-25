package de.meldanor.gronkskyrim.events;

import de.meldanor.gronkskyrim.data.EventData;
import de.meldanor.gronkskyrim.data.PlayerWeight;
import de.meldanor.gronkskyrim.ocr.Frame;

import java.util.List;

public class Event {
    private final Frame frame;
    private PlayerWeight playerWeight;

    public Event(Frame frame) {
        this.frame = frame;
    }

    public void appendData(EventData<?> eventData) {
        if (eventData.getEventType() == EventType.PLAYER_WEIGHT) {
            this.playerWeight = (PlayerWeight) eventData;
        } else {
            throw new RuntimeException("Unsupported event data!");
        }
    }

    public List<EventData<?>> getData(){
        return List.of(this.playerWeight);
    }

    public EventData<?> getDatum(EventType type) {
        if (type == EventType.PLAYER_WEIGHT) {
            return this.playerWeight;
        }
        throw new RuntimeException("Unsupported event data!");
    }
}
