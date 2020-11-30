package de.meldanor.gronkskyrim.serialize.dto;

import de.meldanor.gronkskyrim.Config;
import de.meldanor.gronkskyrim.data.EpisodeBase;
import de.meldanor.gronkskyrim.data.EpisodeMoment;
import de.meldanor.gronkskyrim.data.EventData;
import de.meldanor.gronkskyrim.events.Event;

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

    public Event toOriginal(EpisodeBase episodeBase) {
        int index = (int) (this.timestamp * Config.OCR_FRAMES_PER_SECOND);
        Event event = new Event(new EpisodeMoment(episodeBase, index));
        for (EventDataDto datum : data) {
            if (datum instanceof PlayerWeightDto) {
                event.appendData(((PlayerWeightDto) datum).toOriginal());
            } else if (datum instanceof PlayerGoldDto) {
                event.appendData(((PlayerGoldDto) datum).toOriginal());
            }
        }

        return event;
    }
}
