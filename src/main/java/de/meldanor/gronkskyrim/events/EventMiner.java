package de.meldanor.gronkskyrim.events;

import de.meldanor.gronkskyrim.Config;
import de.meldanor.gronkskyrim.data.EventData;
import de.meldanor.gronkskyrim.data.PlayerGold;
import de.meldanor.gronkskyrim.data.PlayerWeight;
import de.meldanor.gronkskyrim.ocr.Frame;
import de.meldanor.gronkskyrim.ocr.ParseException;
import de.meldanor.gronkskyrim.ocr.Tesseract;
import de.meldanor.gronkskyrim.preprocess.FrameExtractor;
import de.meldanor.gronkskyrim.source.SourceEpisode;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EventMiner {

    private static final Logger LOG = LoggerFactory.getLogger(EventMiner.class.getSimpleName());

    private final FrameExtractor frameExtractor;

    public EventMiner() {
        this.frameExtractor = new FrameExtractor();
    }

    public EpisodeEventLog mineEvents(SourceEpisode episode) {
        EpisodeEventLog log = new EpisodeEventLog(episode);
        File framesDir = frameExtractor.extractFrames(episode);
        LOG.info("Mining events of '{}'...", episode);
        File temporaryFolder;
        try {
            temporaryFolder = Files.createTempDirectory("" + episode.getIndex()).toFile();
            List<Event> events = Files.list(framesDir.toPath())
                    .map(path -> new Frame(episode, path.toFile()))
                    .parallel()
                    .map(frame -> mineEventsOfFrame(frame, temporaryFolder))
                    .collect(Collectors.toList())
                    .stream()
                    .sorted(Comparator.comparing(Event::getFrameTime))
                    .collect(Collectors.toList());

            log.append(events);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        LOG.info("Finished mining events of '{}'!", episode);
        LOG.info("Removing temporary files ...");
        cleanUpTemporaryFiles(temporaryFolder);
        LOG.info("Finished removing temporary files!");

        return log;
    }

    private Event mineEventsOfFrame(Frame frame, File temporaryFolder) {
        Event event = new Event(frame);

        try {
            String text = playerWeightGoldString(frame, temporaryFolder);
            if (text.contains("Traglast")) {
                LOG.debug("Extracting player weight...");
                PlayerWeight weight = tryExtractData(text, PlayerWeight::new);
                if (weight != null) {
                    event.appendData(weight);
                } else {
                    LOG.warn("Can't extract weight from '{}'", text);
                }
                LOG.debug("Finished extracting player weight!");
            }
            if (text.contains("Gold")) {
                LOG.debug("Extracting player gold...");
                PlayerGold gold = tryExtractData(text, PlayerGold::new);
                if (gold != null) {
                    event.appendData(gold);
                } else {
                    LOG.warn("Can't extract gold from '{}'", text);
                }
                LOG.debug("Finished extracting player gold!");
            }
        } catch (Exception e) {
            LOG.error("Extract text of frame '{}'", frame, e);
        }

        return event;
    }

    private <T extends EventData<?>> T tryExtractData(String text, Function<String, T> constructor) {
        try {
            return constructor.apply(text);
        } catch (ParseException e) {
            return null;
        }
    }

    private String playerWeightGoldString(Frame frame, File temporaryFolder) throws Exception {
        // Coords are from a 1080p video the position of the armor, weight and gold
        File file = frame.clipFrame(1090, 980, 650, 60, temporaryFolder);
        return Tesseract.instance().extractText(file);
    }

    private void cleanUpTemporaryFiles(File temporaryFolder) {
        try {
            FileUtils.deleteDirectory(temporaryFolder);
            FileUtils.cleanDirectory(Config.FRAMES_PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
