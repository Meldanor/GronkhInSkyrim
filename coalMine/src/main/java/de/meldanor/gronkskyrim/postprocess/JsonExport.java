package de.meldanor.gronkskyrim.postprocess;

import de.meldanor.gronkskyrim.data.PlayerGold;
import de.meldanor.gronkskyrim.data.PlayerWeight;
import de.meldanor.gronkskyrim.events.EpisodeEventLog;
import de.meldanor.gronkskyrim.events.Event;
import de.meldanor.gronkskyrim.events.EventType;
import de.meldanor.gronkskyrim.events.SeriesEventLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;

public class JsonExport {

    private static final Logger LOG = LoggerFactory.getLogger(JsonExport.class.getSimpleName());

    private static final JsonExport INSTANCE = new JsonExport();

    public static JsonExport getInstance() {
        return INSTANCE;
    }

    private JsonExport() {
    }

    public void exportTo(File file, SeriesEventLog eventLog) throws Exception {
        EpisodeEventLogCompressor compressor = new EpisodeEventLogCompressor();
        try (PrintWriter printer = new PrintWriter(Files.newBufferedWriter(file.toPath()))) {
            printer.print('[');
            double frameTimeOffset = 0;
            for (EpisodeEventLog log : eventLog.getEventLogs()) {
                log = compressor.compress(log);
                for (Event event : log.getEvents()) {

                    PlayerWeight weight = (PlayerWeight) event.getDatum(EventType.PLAYER_WEIGHT);
                    if (weight == null) {
                        continue;
                    }
                    if (weight.getCurrentWeight() > 10_000) {
                        continue;
                    }
                    if (weight.getMaximumWeight() > 1000 && weight.getMaximumWeight() < 2000) {
                        break;
                    }
                    int time = (int) (event.getFrameTime() + frameTimeOffset);
                    printWeight(printer, time, weight);

                }
                frameTimeOffset += log.getEpisode().getLengthSeconds();
            }
            printer.print(']');
        }
        LOG.info("Average compression: {}", compressor.getAverageCompressionString());
    }


    private void printWeight(PrintWriter printer, int time, PlayerWeight weight) {
        printer.print("{\"time\":");
        printer.print(time);
        printer.print(",\"value\":");
        printer.print(weight.getCurrentWeight());
        printer.print(",\"type\":");
        printer.print("\"cur\"");
        printer.println("},");
        printer.print("{\"time\":");
        printer.print(time);
        printer.print(",\"value\":");
        printer.print(weight.getMaximumWeight());
        printer.print(",\"type\":");
        printer.print("\"max\"");
        printer.println("},");
    }


}
