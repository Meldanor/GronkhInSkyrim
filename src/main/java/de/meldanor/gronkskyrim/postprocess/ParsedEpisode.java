package de.meldanor.gronkskyrim.postprocess;

import de.meldanor.gronkskyrim.data.EpisodeBase;

/**
 * A episode that was data mined and gets its data from a persistent EventDataLog.
 */
public class ParsedEpisode extends EpisodeBase {
    protected ParsedEpisode(String name, int index, int lengthSeconds, ParsedSeries series) {
        super(name, index, lengthSeconds, series);
    }
}
