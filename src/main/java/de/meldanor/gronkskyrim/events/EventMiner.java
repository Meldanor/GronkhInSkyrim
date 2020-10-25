package de.meldanor.gronkskyrim.events;

import de.meldanor.gronkskyrim.data.PlayerWeight;
import de.meldanor.gronkskyrim.ocr.Frame;
import de.meldanor.gronkskyrim.ocr.Tesseract;
import de.meldanor.gronkskyrim.preprocess.FrameExtractor;
import de.meldanor.gronkskyrim.source.Episode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EventMiner {

    private static final Logger LOG = LoggerFactory.getLogger(EventMiner.class.getSimpleName());

    private final FrameExtractor frameExtractor;

    public EventMiner() {
        this.frameExtractor = new FrameExtractor();
    }

    public EpisodeEventLog mineEvents(Episode episode) {
        EpisodeEventLog log = new EpisodeEventLog(episode);
        File framesDir = frameExtractor.extractFrames(episode);
        LOG.info("Mining events of '{}'...", episode);
        try {
            List<Event> events = Files.list(framesDir.toPath())
                    .map(path -> new Frame(episode, path.toFile()))
                    .parallel()
                    .map(this::mineEventsOfFrame)
                    .collect(Collectors.toList())
                    .stream()
                    .sorted(Comparator.comparing(Event::getFrameTime))
                    .collect(Collectors.toList());

            log.append(events);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        LOG.info("Finished mining events of '{}'...", episode);
        return log;
    }

    private Event mineEventsOfFrame(Frame frame) {
        Event event = new Event(frame);

        PlayerWeight playerWeight = extractPlayerWeight(frame);
        event.appendData(playerWeight);

        return event;
    }


    private PlayerWeight extractPlayerWeight(Frame frame) {
        LOG.debug("Extracting player weight...");
        try {
            // Coords are from a 1080p video the position of the armor, weight and gold
            File weightFrame = clipFrame(frame, 1090, 980, 560, 60);
            String text = Tesseract.instance().extractText(weightFrame);
            if (text.contains("Traglast")) {
                return new PlayerWeight(text);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            LOG.debug("Finished extracting player weight!");
        }
    }

    private File clipFrame(Frame frame, int x, int y, int width, int height) throws Exception {
        BufferedImage image = ImageIO.read(frame.getFrameFile());
        BufferedImage subimage = image.getSubimage(x, y, width, height);
        File file = File.createTempFile(frame.episodeSecond() + "_playerweight", ".png");
        ImageIO.write(subimage, "png", file);
        return file;
    }
}