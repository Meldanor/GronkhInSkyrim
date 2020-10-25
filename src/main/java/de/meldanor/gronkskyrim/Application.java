package de.meldanor.gronkskyrim;

import de.meldanor.gronkskyrim.events.EpisodeEventLog;
import de.meldanor.gronkskyrim.events.EventMiner;
import de.meldanor.gronkskyrim.events.SeriesEventLog;
import de.meldanor.gronkskyrim.source.Episode;
import de.meldanor.gronkskyrim.source.Series;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.LocalDateTime;

public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class.getSimpleName());

    public static void main(String[] args) throws Exception {
        LOG.info("Hello World, this is GronkhSkyrim");
        LOG.info("Loading the series at '{}' ...", Config.VIDEOS_PATH);

        Series series = new Series(Config.VIDEOS_PATH);
        LOG.info("Extracting events...");
        Episode episode = series.getEpisode(2);
        EventMiner eventMiner = new EventMiner();
        SeriesEventLog seriesEventLog = new SeriesEventLog(series);
        EpisodeEventLog episodeEventLog = eventMiner.mineEvents(episode);
        seriesEventLog.addEpisodeLog(episodeEventLog);

        File output = new File(Config.EVENT_LOG_PATH, seriesEventLog.getDirectoryName(LocalDateTime.now()));
        output.mkdirs();
        seriesEventLog.writeTo(output);

//        episodeEventLog.writeTo(new File(Config.EVENT_LOG_PATH,episodeEventLog.getLogName()));
        LOG.info("Finished!");
//        frameExtractor.extractFrames(series.getEpisode(2));
//        List<Path> frameFiles = Files.list(Path.of("/frames")).collect(Collectors.toList());
//        AtomicInteger counter = new AtomicInteger();
//        TreeMap<Integer, PlayerWeight> treeMap = frameFiles.stream()
//                .parallel()
//                .map(path -> {
//                    try {
//                        Frame frame = new Frame(series.getEpisode(1), path.toFile());
//                        WeightExtractor weightExtractor = new WeightExtractor();
//                        int localCounter = counter.getAndIncrement();
//                        double percent = ((double) localCounter) / (double) (frameFiles.size());
//                        String s = DecimalFormat.getPercentInstance().format(percent);
//                        LOG.info("Parsing {}/{} ({}) frame", localCounter, frameFiles.size(), s);
//                        return Map.entry(frame.episodeSecond(), weightExtractor.extract(frame));
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                })
//                .filter(e -> e.getValue().isPresent())
//
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        e -> e.getValue().get(),
//                        (o1, o2) -> o1,
//                        TreeMap::new
//                ));
//
//        for (Map.Entry<Integer, PlayerWeight> entry : treeMap.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }
//

    }
}
