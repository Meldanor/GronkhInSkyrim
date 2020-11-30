package de.meldanor.gronkskyrim.postprocess;

import de.meldanor.gronkskyrim.data.EpisodeBase;
import de.meldanor.gronkskyrim.serialize.dto.EpisodeBaseDto;

import java.io.File;

/**
 * A episode that was data mined and gets its data from a persistent EventDataLog.
 */
public class ParsedEpisode extends EpisodeBase {
    private final File logFile;

    protected ParsedEpisode(File logFile, EpisodeBaseDto episodeBaseDto, ParsedSeries series) {
        super(episodeBaseDto.getName(), episodeBaseDto.getIndex(), episodeBaseDto.getLengthSeconds(), series);
        this.logFile = logFile;
    }

    public File getLogFile() {
        return logFile;
    }
}
