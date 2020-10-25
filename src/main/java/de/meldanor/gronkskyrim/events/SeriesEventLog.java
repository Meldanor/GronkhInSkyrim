package de.meldanor.gronkskyrim.events;

import de.meldanor.gronkskyrim.source.Series;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SeriesEventLog {

    private static final Logger LOG = LoggerFactory.getLogger(SeriesEventLog.class.getSimpleName());

    private final Series series;
    private List<EpisodeEventLog> eventLogs;

    public SeriesEventLog(Series series) {
        this.series = series;
        this.eventLogs = new ArrayList<>(series.getEpisodes().size());
    }

    public void addEpisodeLog(EpisodeEventLog episodeEventLog) {
        this.eventLogs.add(episodeEventLog);
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd'__'HHmmss");

    public String getDirectoryName(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER) + "__" +
                this.series.getEpisodes().size() + "__" +
                this.series.getTotalEpisodeLengthSeconds();
    }

    public void writeTo(File directory) {
        LOG.info("Writing series event log to '{}'...", directory);

        for (int i = 0; i < eventLogs.size(); i++) {
            EpisodeEventLog eventLog = eventLogs.get(i);
            File f = new File(directory, eventLog.getLogName());
            LOG.info("({}/{}) Writing event log to {}", i + 1, eventLogs.size(), f);
            eventLog.writeTo(f);
        }
        LOG.info("Finished writing series event log to '{}'...", directory);
    }
}
