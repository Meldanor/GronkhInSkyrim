package de.meldanor.gronkskyrim.events;

import de.meldanor.gronkskyrim.source.SourceSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SeriesEventLog {

    private static final Logger LOG = LoggerFactory.getLogger(SeriesEventLog.class.getSimpleName());

    private final File episodeLogDirectory;
    private final SourceSeries series;
//    private List<EpisodeEventLog> eventLogs;

    public SeriesEventLog(SourceSeries series, File logDirectory) {
        this.series = series;
        this.episodeLogDirectory = new File(logDirectory, getDirectoryName(LocalDateTime.now()));
        if (!episodeLogDirectory.mkdirs()) {
            throw new RuntimeException("Can't create directory '" + episodeLogDirectory + "'");
        }
//        this.eventLogs = new ArrayList<>(series.getEpisodes().size());
    }

    public void addEpisodeLog(EpisodeEventLog episodeEventLog) {
        // TODO: store the event logs in the list and post process the logs instead of directly writing them to the log
        File f = new File(this.episodeLogDirectory, episodeEventLog.getLogName());
        LOG.info(" Writing event log of episode {} to {}", episodeEventLog.getEpisode(), f);
        episodeEventLog.writeTo(f);
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd'__'HHmmss");

    private String getDirectoryName(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER) + "__" +
                this.series.getEpisodes().size() + "__" +
                this.series.getTotalEpisodeLengthSeconds();
    }

//    public void writeTo(File directory) {
//        LOG.info("Writing series event log to '{}'...", directory);
//
//        for (int i = 0; i < eventLogs.size(); i++) {
//            EpisodeEventLog eventLog = eventLogs.get(i);
//            File f = new File(directory, eventLog.getLogName());
//            LOG.info("({}/{}) Writing event log to {}", i + 1, eventLogs.size(), f);
//            eventLog.writeTo(f);
//        }
//        LOG.info("Finished writing series event log to '{}'...", directory);
//    }
}
