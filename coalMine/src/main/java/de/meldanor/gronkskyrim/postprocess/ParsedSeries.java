package de.meldanor.gronkskyrim.postprocess;

import de.meldanor.gronkskyrim.data.EpisodeBase;
import de.meldanor.gronkskyrim.data.SeriesBase;
import de.meldanor.gronkskyrim.serialize.dto.EpisodeEventLogDto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static de.meldanor.gronkskyrim.Util.JSON;

/**
 * A series that was data mined and gets its data from a persistent SeriesEventLog.
 */
public class ParsedSeries extends SeriesBase<ParsedEpisode> {

    private final File seriesLogDir;

    public ParsedSeries(File seriesLogDir) throws Exception {
        super(seriesLogDir);
        this.seriesLogDir = seriesLogDir;
    }

    @Override
    protected List<ParsedEpisode> parseEpisodes(File seriesDir) throws Exception {
        return Files.list(seriesDir.toPath())
                .map(logFile -> loadEpisode(logFile.toFile()))
                .sorted(Comparator.comparing(EpisodeBase::getIndex))
                .collect(Collectors.toList());
    }

    private ParsedEpisode loadEpisode(File file) {
        EpisodeEventLogDto eventLog;
        try {
            eventLog = JSON.readValue(file, EpisodeEventLogDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ParsedEpisode(file, eventLog.getEpisodeMeta(), this);
    }

    public File getSeriesLogDir() {
        return seriesLogDir;
    }

    @Override
    public String toString() {
        return "ParsedSeries{" +
                "seriesLogDir=" + seriesLogDir +
                "} " + super.toString();
    }
}
