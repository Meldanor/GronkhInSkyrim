package de.meldanor.gronkskyrim.postprocess;

import de.meldanor.gronkskyrim.data.EpisodeBase;

import java.io.File;

/**
 * A episode that was data mined and gets its data from a persistent EventDataLog.
 */
public class ParsedEpisode extends EpisodeBase {
    private final File logFile;

    protected ParsedEpisode(File logFile, String name, int index, int lengthSeconds, ParsedSeries series) {
        super(name, index, lengthSeconds, series);
        this.logFile = logFile;
    }

    public File getLogFile() {
        return logFile;
    }
}
