package de.meldanor.gronkskyrim.data;

import de.meldanor.gronkskyrim.events.EventType;
import de.meldanor.gronkskyrim.serialize.dto.EventDataDto;

public interface EventData<T> {
    EventType getEventType();

    EventDataDto toSerializable();
}
