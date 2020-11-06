package de.meldanor.gronkskyrim.data;

import java.io.File;
import java.util.List;

public abstract class SeriesBase<T extends EpisodeBase> {

    protected final List<T> episodes;
    protected final int totalEpisodeLengthSeconds;

    public SeriesBase(File seriesDir) throws Exception {
        this.episodes = this.parseEpisodes(seriesDir);
        this.totalEpisodeLengthSeconds = this.episodes
                .stream()
                .mapToInt(EpisodeBase::getLengthSeconds)
                .sum();
    }

    protected abstract List<T> parseEpisodes(File seriesDir) throws Exception;

    public List<T> getEpisodes() {
        return episodes;
    }

    /**
     * @param episodeIndex 1 based index (as the video title)
     * @return The episode at that index
     */
    public T getEpisode(int episodeIndex) {
        return getEpisodes().get(episodeIndex - 1);
    }

    public int getTotalEpisodeLengthSeconds() {
        return totalEpisodeLengthSeconds;
    }
}
