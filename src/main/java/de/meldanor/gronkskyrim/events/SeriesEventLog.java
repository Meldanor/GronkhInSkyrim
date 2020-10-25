package de.meldanor.gronkskyrim.events;

import de.meldanor.gronkskyrim.source.Series;

import java.util.ArrayList;
import java.util.List;

public class SeriesEventLog {
    private final Series series;
    private List<EpisodeEventLog> eventLogs;

    public SeriesEventLog(Series series) {
        this.series = series;
        this.eventLogs = new ArrayList<>(series.getEpisodes().size());
    }
}
