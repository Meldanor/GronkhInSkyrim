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

public class CsvExport {

    private static final CsvExport INSTANCE = new CsvExport();

    public static CsvExport getInstance() {
        return INSTANCE;
    }

    private CsvExport() {
    }

    public void exportTo(File file, SeriesEventLog eventLog) throws Exception {
        try (PrintWriter printer = new PrintWriter(Files.newBufferedWriter(file.toPath()))) {
            printer.println("Timestamp;CurWeight;MaxWeight;Gold");
            double frameTimeOffset = 0;
            for (EpisodeEventLog log : eventLog.getEventLogs()) {
                log = EpisodeEventLogCompressor.getInstance().compress(log);
                for (Event event : log.getEvents()) {
                    printer.print(event.getFrameTime() + frameTimeOffset);
                    printer.print(';');
                    PlayerWeight weight = (PlayerWeight) event.getDatum(EventType.PLAYER_WEIGHT);
                    if (weight != null) {
                        printer.print(weight.getCurrentWeight());
                        printer.print(';');
                        printer.print(weight.getMaximumWeight());
                    } else {
                        printer.print(';');
                    }
                    printer.print(';');
                    PlayerGold gold = (PlayerGold) event.getDatum(EventType.PLAYER_GOLD);
                    if (gold != null) {
                        printer.print(gold.getGold());
                    }
                    printer.println();
                }
                frameTimeOffset += log.getEpisode().getLengthSeconds();
            }
        }
    }
}
