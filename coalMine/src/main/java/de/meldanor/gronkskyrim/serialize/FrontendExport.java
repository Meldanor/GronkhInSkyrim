package de.meldanor.gronkskyrim.serialize;

import de.meldanor.gronkskyrim.data.EpisodeBase;
import de.meldanor.gronkskyrim.data.PlayerGold;
import de.meldanor.gronkskyrim.data.PlayerWeight;
import de.meldanor.gronkskyrim.events.EpisodeEventLog;
import de.meldanor.gronkskyrim.events.Event;
import de.meldanor.gronkskyrim.events.EventType;
import de.meldanor.gronkskyrim.events.SeriesEventLog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static de.meldanor.gronkskyrim.Util.JSON;

/**
 * Exports a series log in a format that the frontend can work with it
 */
public class FrontendExport {

    public void exportTo(SeriesEventLog seriesEventLog, File targetDir) throws Exception {
        // Compress in general
        seriesEventLog = seriesEventLog.compress();
        // Compress additionally by weight
        writeWeightData(seriesEventLog.compress(EventType.PLAYER_WEIGHT), targetDir);
        // Compress additionally by gold
        writeGoldData(seriesEventLog.compress(EventType.PLAYER_GOLD), targetDir);
        writeSeriesData(seriesEventLog, targetDir);
    }

    private void writeSeriesData(SeriesEventLog seriesEventLog, File dir) throws Exception {
        List<Map<String, Object>> mapWeight = mapEpisodeMeta(seriesEventLog);
        JSON.writeValue(new File(dir, "series_meta.json"), mapWeight);
    }

    private List<Map<String, Object>> mapEpisodeMeta(SeriesEventLog seriesEventLog) {
        List<? extends EpisodeBase> episodes = seriesEventLog.getSeries().getEpisodes();
        List<Map<String, Object>> episodeData = new ArrayList<>(episodes.size());
        long timeOffset = 0;
        for (EpisodeBase episode : episodes) {
            episodeData.add(
                    Map.of("timestamp", timeOffset,
                            "episodeName", episode.getName()
                    ));
            timeOffset += episode.getLengthSeconds();
        }

        return episodeData;
    }

    private void writeWeightData(SeriesEventLog seriesEventLog, File dir) throws Exception {
        List<Map<String, Object>> mapWeight = mapWeight(seriesEventLog.getEventLogs());
        JSON.writeValue(new File(dir, "weights.json"), mapWeight);
    }

    private List<Map<String, Object>> mapWeight(List<EpisodeEventLog> episodeEventLogs) {
        long timeOffset = 0;
        List<Map<String, Object>> episodeData = new ArrayList<>();
        for (EpisodeEventLog eventLog : episodeEventLogs) {
            EpisodeBase episode = eventLog.getEpisode();
            List<Event> events = eventLog.getEvents();
            for (Event event : events) {
                PlayerWeight playerWeight = (PlayerWeight) event.getDatum(EventType.PLAYER_WEIGHT);
                if (playerWeight != null) {
                    episodeData.add(
                            Map.of(
                                    "timestamp", (int) (event.getFrameTime() + timeOffset),
                                    "value", playerWeight.getCurrentWeight(),
                                    "type", "cur"
                            )

                    );
                    episodeData.add(
                            Map.of(
                                    "timestamp", (int) (event.getFrameTime() + timeOffset),
                                    "value", playerWeight.getMaximumWeight(),
                                    "type", "max"
                            )

                    );
                }
            }

            timeOffset += episode.getLengthSeconds();
        }
        return episodeData;
    }

    private void writeGoldData(SeriesEventLog seriesEventLog, File dir) throws Exception {
        List<Map<String, Object>> mapWeight = mapGold(seriesEventLog.getEventLogs());
        JSON.writeValue(new File(dir, "gold.json"), mapWeight);
    }

    private List<Map<String, Object>> mapGold(List<EpisodeEventLog> episodeEventLogs) {
        long timeOffset = 0;
        List<Map<String, Object>> episodeData = new ArrayList<>();
        for (EpisodeEventLog eventLog : episodeEventLogs) {
            EpisodeBase episode = eventLog.getEpisode();
            List<Event> events = eventLog.getEvents();
            for (Event event : events) {
                PlayerGold playerGold = (PlayerGold) event.getDatum(EventType.PLAYER_GOLD);
                if (playerGold != null) {
                    episodeData.add(
                            Map.of(
                                    "timestamp", (int) (event.getFrameTime() + timeOffset),
                                    "value", playerGold.getGold()
                            )
                    );
                }
            }

            timeOffset += episode.getLengthSeconds();
        }
        return episodeData;
    }
}
