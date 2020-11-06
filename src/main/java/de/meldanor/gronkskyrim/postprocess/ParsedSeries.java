package de.meldanor.gronkskyrim.postprocess;

import de.meldanor.gronkskyrim.data.EpisodeBase;
import de.meldanor.gronkskyrim.data.SeriesBase;

import java.io.File;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A series that was data mined and gets its data from a persistent SeriesEventLog.
 */
public class ParsedSeries extends SeriesBase<ParsedEpisode> {

    private static final ParsedEpisodeFactory EPISODE_FACTORY = ParsedEpisodeFactory.instance();

    public ParsedSeries(File seriesLogDir) throws Exception {
        super(seriesLogDir);
    }

    @Override
    protected List<ParsedEpisode> parseEpisodes(File seriesDir) throws Exception {
        return Files.list(seriesDir.toPath())
                .map(p -> EPISODE_FACTORY.createEpisode(this, p.toFile()))
                .sorted(Comparator.comparing(EpisodeBase::getIndex))
                .collect(Collectors.toList());
    }
}
