package de.meldanor.gronkskyrim.events;

import de.meldanor.gronkskyrim.data.EventData;
import de.meldanor.gronkskyrim.source.Episode;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the log of events of a single Episode. The log is a time sorted list of events.
 * <p>
 * The persisted EventLog is looking like:
 * <pre>
 *     EVENT_TYPE=EVENT_VALUE;;;EVENT_TYPE=EVENT_VALUE;;;...;;;EVENT_TYPE=EVENT_VALUE;;;
 * </pre>
 * <p>
 * The name of the file is define
 */
public class EpisodeEventLog {
    private final Episode episode;
    private List<Event> events;

    public EpisodeEventLog(Episode episode) {
        this.episode = episode;
        this.events = new ArrayList<>();
    }

    public void append(Event event) {
        this.events.add(event);
    }

    public Episode getEpisode() {
        return episode;
    }

    public List<Event> getEvents() {
        return events;
    }

    /**
     * @return EpisodeIndex__EpisodeLengthSeconds__EpisodeName
     */
    public String getLogName() {
        String indexString = String.format("%04d", this.episode.getIndex());
        return indexString + "__" + this.episode.getLengthSeconds() + "s__" + this.episode.getName();
    }

    public void writeTo(File file) {
        try (PrintWriter printer = new PrintWriter(Files.newBufferedWriter(file.toPath()))) {
            for (Event event : events) {
                String line = event.getData()
                        .stream()
                        .map(EventData::toEventLogString)
                        .collect(Collectors.joining(";;;"));
                printer.println(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
