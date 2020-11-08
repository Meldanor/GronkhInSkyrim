package de.meldanor.gronkskyrim.data;

import de.meldanor.gronkskyrim.events.EventType;

public interface EventData<T> {
    EventType getEventType();

    String toEventLogString();
}
