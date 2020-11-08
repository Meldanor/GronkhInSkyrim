package de.meldanor.gronkskyrim.postprocess;

import de.meldanor.gronkskyrim.data.PlayerGold;
import de.meldanor.gronkskyrim.data.PlayerWeight;
import de.meldanor.gronkskyrim.events.EpisodeEventLog;
import de.meldanor.gronkskyrim.events.Event;
import de.meldanor.gronkskyrim.events.EventType;
import de.meldanor.gronkskyrim.events.SeriesEventLog;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;

public class JsonExport {

    private static final JsonExport INSTANCE = new JsonExport();

    public static JsonExport getInstance() {
        return INSTANCE;
    }

    private JsonExport() {
    }
    public void exportTo(File file, SeriesEventLog eventLog) throws Exception {
        try (PrintWriter printer = new PrintWriter(Files.newBufferedWriter(file.toPath()))) {
            printer.print('[');
            double frameTimeOffset = 0;
            for (EpisodeEventLog log : eventLog.getEventLogs()) {
                log = EpisodeEventLogCompressor.getInstance().compress(log);
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
                    printWeight(printer, time, "Aktuel", weight.getCurrentWeight());
                    printWeight(printer, time, "Maximum", weight.getMaximumWeight());

                }
                frameTimeOffset += log.getEpisode().getLengthSeconds();
            }
            printer.print(']');
        }
    }

    private void printWeight(PrintWriter printer, int time, String field, int value) {
        printer.print("{\"timestamp\":");
        printer.print(time);
        printer.print(",\"type\":\"");
        // { type: 'curWeight', timestamp: 2667, value: 119 }];
        printer.print(field);
        printer.print("\",\"value\":");
        printer.print(value);
        printer.println("},");
    }
}
