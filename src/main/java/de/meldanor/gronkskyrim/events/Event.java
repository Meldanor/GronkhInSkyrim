package de.meldanor.gronkskyrim.events;

import de.meldanor.gronkskyrim.data.EventData;
import de.meldanor.gronkskyrim.data.PlayerWeight;
import de.meldanor.gronkskyrim.ocr.Frame;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private final Frame frame;
    private PlayerWeight playerWeight;

    public Event(Frame frame) {
        this.frame = frame;
    }

    public void appendData(EventData<?> eventData) {
        if (eventData == null) {
            return;
        }
        if (eventData.getEventType() == EventType.PLAYER_WEIGHT) {
            this.playerWeight = (PlayerWeight) eventData;
        } else {
            throw new RuntimeException("Unsupported event data!");
        }
    }

    public List<EventData<?>> getData() {
        List<EventData<?>> data = new ArrayList<>();
        if (this.playerWeight != null) {
            data.add(playerWeight);
        }

        return data;
    }

    public EventData<?> getDatum(EventType type) {
        if (type == EventType.PLAYER_WEIGHT) {
            return this.playerWeight;
        }
        throw new RuntimeException("Unsupported event data!");
    }

    public int getFrameTime() {
        return this.frame.episodeSecond();
    }
}
