package de.meldanor.gronkskyrim;

import de.meldanor.gronkskyrim.events.EpisodeEventLog;
import de.meldanor.gronkskyrim.events.EventMiner;
import de.meldanor.gronkskyrim.events.SeriesEventLog;
import de.meldanor.gronkskyrim.postprocess.CsvExport;
import de.meldanor.gronkskyrim.postprocess.ParsedSeries;
import de.meldanor.gronkskyrim.source.SourceEpisode;
import de.meldanor.gronkskyrim.source.SourceSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.File;
import java.util.concurrent.Callable;

public class Application implements Callable<Integer> {
    @CommandLine.Option(names = {"-t", "--type"}, required = true)
    private ApplicationType type;

    private static final Logger LOG = LoggerFactory.getLogger(Application.class.getSimpleName());

    public static void main(String[] args) {
        LOG.info("Hello World, this is GronkhSkyrim");
        int exitCode = new CommandLine(new Application()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        switch (type) {
            case ANALYZE -> analyze();
            case POSTPROCESS -> postprocess();
        }
        return 0;
    }

    private void analyze() throws Exception {
        LOG.info("Starting analysing process...");
        LOG.info("Loading the series at '{}' ...", Config.VIDEOS_PATH);

        SourceSeries series = new SourceSeries(Config.VIDEOS_PATH);
        LOG.info("Extracting events...");
        EventMiner eventMiner = new EventMiner();
        SeriesEventLog seriesEventLog = new SeriesEventLog(series, Config.EVENT_LOG_PATH);
        for (SourceEpisode episode : series.getEpisodes()) {
            LOG.info("Extracting events from episode {}...", episode);
            EpisodeEventLog episodeEventLog = eventMiner.mineEvents(episode);
            seriesEventLog.addEpisodeLog(episodeEventLog);
            LOG.info("Finished extracting events from episode {}!", episode);
        }

        LOG.info("Finished!");
    }

    private void postprocess() throws Exception {
        LOG.info("Starting post processing...");
        ParsedSeries series = new ParsedSeries(Config.POST_PROCESS_SERIES_LOG_PATH);
        SeriesEventLog eventLog = new SeriesEventLog(series);
        File file = new File("output.csv");
        CsvExport.getInstance().exportTo(file, eventLog);

        LOG.info("Finished!");
    }
}
