package de.meldanor.gronkskyrim;

import de.meldanor.gronkskyrim.data.PlayerWeight;
import de.meldanor.gronkskyrim.ocr.Frame;
import de.meldanor.gronkskyrim.ocr.WeightExtractor;
import de.meldanor.gronkskyrim.source.Episode;
import de.meldanor.gronkskyrim.source.EpisodeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Application {

    public final static File FFMPEG_PATH = new File("/usr/bin/ffmpeg");
    public final static File FFPROBE_PATH = new File("/usr/bin/ffprobe");
    public final static File TESSERACT_PATH = new File("/usr/bin/tesseract");

    private static final Logger LOG = LoggerFactory.getLogger(Application.class.getSimpleName());

    public static void main(String[] args) throws Exception {
        LOG.info("Hello World, this is GronkhSkyrim");
        File file = new File("/videos/002 - Malerisch, Wunderschön, SEHR AGGRESSIV.mkv");
        Episode episode = EpisodeFactory.instance().createEpisode(file);
        List<Path> frameFiles = Files.list(Path.of("/frames")).collect(Collectors.toList());
        AtomicInteger counter = new AtomicInteger();
        TreeMap<Integer, PlayerWeight> treeMap = frameFiles.stream()
                .parallel()
                .map(path -> {
                    try {
                        Frame frame = new Frame(episode, path.toFile());
                        WeightExtractor weightExtractor = new WeightExtractor();
                        int localCounter = counter.getAndIncrement();
                        double percent = ((double)localCounter)/(double)(frameFiles.size()) ;
                        String s = DecimalFormat.getPercentInstance().format(percent);
                        LOG.info("Parsing {}/{} ({}) frame", localCounter, frameFiles.size(),s);
                        return Map.entry(frame.episodeSecond(), weightExtractor.extract(frame));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(e -> e.getValue().isPresent())

                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().get(),
                        (o1, o2) -> o1,
                        TreeMap::new
                ));

        for (Map.Entry<Integer, PlayerWeight> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
