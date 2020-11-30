package de.meldanor.gronkskyrim.serialize.dto;

import de.meldanor.gronkskyrim.data.EventData;

import java.util.List;
import java.util.stream.Collectors;

public class EventDto {
    private long timestamp;
    private List<EventDataDto> data;

    public EventDto() {
    }

    public EventDto(de.meldanor.gronkskyrim.events.Event event) {
        this.timestamp = (long) event.getFrameTime();
        this.data = event.getData()
                .stream()
                .map(EventData::toSerializable)
                .collect(Collectors.toList());
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<EventDataDto> getData() {
        return data;
    }

    public void setData(List<EventDataDto> data) {
        this.data = data;
    }
}
