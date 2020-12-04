package de.meldanor.gronkskyrim.postprocess;

import de.meldanor.gronkskyrim.data.EventData;
import de.meldanor.gronkskyrim.events.EpisodeEventLog;
import de.meldanor.gronkskyrim.events.Event;
import de.meldanor.gronkskyrim.events.EventType;
import de.meldanor.gronkskyrim.util.RunningAverage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The classes removes redundant Events from an EpisodeEventLog. If there is no change between two timestamps only
 * the start and the end in that range are stored instead of all data points.
 * <p>
 * We can use this because the OCR will often report the same value even if there nothing happening.
 * <p>
 * The advantage of the compression is a way smaller file size, which results in a faster visualization and in
 * a clearer visualization because there are fewer data points at the same position in time.
 */
public class EpisodeEventLogCompressor {

    private static final Logger LOG = LoggerFactory.getLogger(EpisodeEventLogCompressor.class.getSimpleName());
    private static final NumberFormat PERCENTAGE = DecimalFormat.getPercentInstance();

    public EpisodeEventLog compress(EpisodeEventLog episodeEventLog, RunningAverage averageCompression) {
        List<Event> events = episodeEventLog.getEvents();
        if (events.isEmpty()) {
            LOG.debug("Nothing to compress for {}", episodeEventLog.getEpisode());
            return episodeEventLog;
        }
        List<Event> compressedEvents = compressEvents(events);
        return postCompress(compressedEvents, events, episodeEventLog, averageCompression);
    }

    public EpisodeEventLog compress(EpisodeEventLog episodeEventLog, EventType eventType,
                                    RunningAverage averageCompression) {
        List<Event> events = episodeEventLog.getEvents();
        if (events.isEmpty()) {
            LOG.debug("Nothing to compress for {}", episodeEventLog.getEpisode());
            return episodeEventLog;
        }
        List<Event> compressedEvents = compressByTypeEvents(events, eventType);
        return postCompress(compressedEvents, events, episodeEventLog, averageCompression);
    }

    private List<Event> compressEvents(List<Event> events) {
        List<Event> compressedEvents = new ArrayList<>();
        Event start = events.get(0);
        for (int i = 1; i < events.size(); i++) {
            Event event = events.get(i);
            if (!start.hasSameData(event)) {
                compressedEvents.add(start);
                // Not at the end of log
                if (i + 1 < events.size()) {
                    start = events.get(++i);
                }
            }
        }
        return compressedEvents;
    }

    private List<Event> compressByTypeEvents(List<Event> events, EventType eventType) {
        List<Event> compressedEvents = new ArrayList<>();
        Event start = events.get(0);
        EventData<?> startDatum = start.getDatum(eventType);
        for (int i = 1; i < events.size(); i++) {
            Event event = events.get(i);
            EventData<?> datum = event.getDatum(eventType);
            if (!Objects.equals(startDatum, datum)) {
                compressedEvents.add(start);
                // Not at the end of log
                if (i + 1 < events.size()) {
                    start = events.get(++i);
                    startDatum = datum;
                }
            }
        }
        return compressedEvents;
    }

    private EpisodeEventLog postCompress(List<Event> compressedEvents, List<Event> events,
                                         EpisodeEventLog episodeEventLog, RunningAverage runningAverage) {
        compressedEvents.add(events.get(events.size() - 1));
        double compression = (double) compressedEvents.size() / (double) events.size();
        runningAverage.add(compression);
        LOG.debug("Compress ration {} for {}", PERCENTAGE.format(compression), episodeEventLog.getEpisode());
        EpisodeEventLog compressedLog = new EpisodeEventLog(episodeEventLog.getEpisode());
        compressedLog.append(compressedEvents);
        return compressedLog;
    }
}
