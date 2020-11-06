package de.meldanor.gronkskyrim.postprocess;

import de.meldanor.gronkskyrim.data.EpisodeBase;
import de.meldanor.gronkskyrim.data.SeriesBase;

import java.util.concurrent.Callable;

public class CsvExport implements Callable<Void> {
    private final SeriesBase<? extends EpisodeBase> series;

    public CsvExport(SeriesBase<? extends EpisodeBase> series) {

        this.series = series;
    }

    @Override
    public Void call() throws Exception {

        System.out.println(series.getTotalEpisodeLengthSeconds());
        return null;
    }


}
