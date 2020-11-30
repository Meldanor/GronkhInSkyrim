package de.meldanor.gronkskyrim.serialize.dto;

import de.meldanor.gronkskyrim.events.EpisodeEventLog;
import de.meldanor.gronkskyrim.events.Event;

import java.util.List;
import java.util.stream.Collectors;

public class EpisodeEventLogDto {
    private EpisodeBaseDto episodeMeta;
    private List<EventDto> events;

    public EpisodeEventLogDto() {
    }

    public EpisodeEventLogDto(EpisodeEventLog episodeEventLog) {
        this.episodeMeta = new EpisodeBaseDto(episodeEventLog.getEpisode());
        this.events = episodeEventLog.getEvents()
                .stream()
                .map(Event::toSerializable)
                .filter(e -> !e.getData().isEmpty())
                .collect(Collectors.toList());
    }

    public EpisodeBaseDto getEpisodeMeta() {
        return episodeMeta;
    }

    public void setEpisodeMeta(EpisodeBaseDto episodeMeta) {
        this.episodeMeta = episodeMeta;
    }

    public List<EventDto> getEvents() {
        return events;
    }

    public void setEvents(List<EventDto> events) {
        this.events = events;
    }
}
