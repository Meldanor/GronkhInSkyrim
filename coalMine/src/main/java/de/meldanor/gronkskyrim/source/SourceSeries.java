package de.meldanor.gronkskyrim.source;

import de.meldanor.gronkskyrim.data.EpisodeBase;
import de.meldanor.gronkskyrim.data.SeriesBase;

import java.io.File;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A series that needs to be parsed and data mined. The series directory contains the video files.
 */
public class SourceSeries extends SeriesBase<SourceEpisode> {

    private final static SourceEpisodeFactory EPISODE_FACTORY = SourceEpisodeFactory.instance();

    public SourceSeries(File seriesDir) throws Exception {
        super(seriesDir);
    }

    protected List<SourceEpisode> parseEpisodes(File seriesDir) throws Exception {
        return Files.list(seriesDir.toPath())
                .map(p -> EPISODE_FACTORY.createEpisode(this, p.toFile()))
                .sorted(Comparator.comparing(EpisodeBase::getIndex))
                .collect(Collectors.toList());
    }
}
