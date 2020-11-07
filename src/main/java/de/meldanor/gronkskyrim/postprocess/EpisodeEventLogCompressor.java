package de.meldanor.gronkskyrim.postprocess;

import de.meldanor.gronkskyrim.events.EpisodeEventLog;
import de.meldanor.gronkskyrim.events.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

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
    private static final EpisodeEventLogCompressor INSTANCE = new EpisodeEventLogCompressor();

    private EpisodeEventLogCompressor() {

    }

    public static EpisodeEventLogCompressor getInstance() {
        return INSTANCE;
    }

    private static final Logger LOG = LoggerFactory.getLogger(EpisodeEventLogCompressor.class.getSimpleName());
    private static final NumberFormat PERCENTAGE = DecimalFormat.getPercentInstance();

    public EpisodeEventLog compress(EpisodeEventLog episodeEventLog) {
        if (episodeEventLog.getEvents().isEmpty()) {
            LOG.warn("Nothing to compress");
            return episodeEventLog;
        }
        List<Event> compressedEvents = new ArrayList<>();
        List<Event> events = episodeEventLog.getEvents();
        Event start = events.get(0);
        int startHash = start.calculateHash();
        for (int i = 1; i < events.size(); i++) {
            Event event = events.get(i);
            int curHash = event.calculateHash();
            if (startHash != curHash) {
                compressedEvents.add(start);
                compressedEvents.add(event);
                // Not at the end of log
                if (i + 1 < events.size()) {
                    start = events.get(++i);
                    startHash = start.calculateHash();
                }
            }
        }
        compressedEvents.add(start);
        compressedEvents.add(events.get(events.size() - 1));
        double compression = (double) compressedEvents.size() / (double) events.size();
        LOG.info("Compress ration for episode {} is {}", episodeEventLog.getEpisode(), PERCENTAGE.format(compression));
        EpisodeEventLog compressedLog = new EpisodeEventLog(episodeEventLog.getEpisode());
        compressedLog.append(compressedEvents);

        return compressedLog;
    }
}
