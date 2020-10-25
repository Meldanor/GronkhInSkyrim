package de.meldanor.gronkskyrim.source;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class Series {
    private final static EpisodeFactory EPISODE_FACTORY = EpisodeFactory.instance();
    private final List<Episode> episodes;
    private final int totalEpisodeLengthSeconds;

    public Series(File seriesDir) throws Exception{
        this.episodes = parseEpisodes(seriesDir);
        this.totalEpisodeLengthSeconds = this.episodes
                .stream()
                .mapToInt(Episode::getLengthSeconds)
                .sum();
    }

    private List<Episode> parseEpisodes(File seriesDir) throws Exception{
        return Files.list(seriesDir.toPath())
                .map(p -> EPISODE_FACTORY.createEpisode(p.toFile()))
                .collect(Collectors.toList());
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    /**
     *
     * @param episodeIndex 1 based index (as the video title
     * @return
     */
    public Episode getEpisode(int episodeIndex) {
        return getEpisodes().get(episodeIndex - 1);
    }


}
