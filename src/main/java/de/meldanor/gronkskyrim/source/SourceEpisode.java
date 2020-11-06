package de.meldanor.gronkskyrim.source;

import de.meldanor.gronkskyrim.data.EpisodeBase;

import java.io.File;

/**
 * Episodes data based on the video files. These information from the episodes needs to be data mined.
 */
public final class SourceEpisode extends EpisodeBase {

    private final File file;

    SourceEpisode(File file, String name, int index, int lengthSeconds, SourceSeries series) {
        super(name, index, lengthSeconds, series);
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "file=" + file +
                "} " + super.toString();
    }
}
