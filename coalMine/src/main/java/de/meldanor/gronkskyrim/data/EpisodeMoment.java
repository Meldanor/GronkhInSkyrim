package de.meldanor.gronkskyrim.data;

import de.meldanor.gronkskyrim.Config;

public class EpisodeMoment {
    private final EpisodeBase episode;
    private final int index;

    public EpisodeMoment(EpisodeBase episode, int index) {
        this.episode = episode;
        this.index = index;
    }

    // es = index / ocr_frames
    // index = es * ocr_frames
    public double episodeSecond() {
        return (double) this.index / (double) Config.OCR_FRAMES_PER_SECOND;
    }

    public EpisodeBase getEpisode() {
        return episode;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "EpisodeMoment{" +
                "index=" + index +
                '}';
    }
}
