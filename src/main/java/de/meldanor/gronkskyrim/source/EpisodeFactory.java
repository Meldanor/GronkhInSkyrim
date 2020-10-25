package de.meldanor.gronkskyrim.source;

import de.meldanor.gronkskyrim.Config;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EpisodeFactory {
    private static final EpisodeFactory instance = new EpisodeFactory();

    public static EpisodeFactory instance() {
        return instance;
    }

    private EpisodeFactory() {

    }

    private static final Pattern EPISODE_NAME_PATTERN = Pattern.compile("(\\d{3}) - (.*).(?:.{3,4})");

    public Episode createEpisode(Series series, File file) {
        String fileName = file.getName();
        Matcher matcher = EPISODE_NAME_PATTERN.matcher(fileName);
        String name;
        int index;
        if (matcher.find()) {
            index = Integer.parseInt(matcher.group(1));
            name = matcher.group(2);
        } else {
            throw new RuntimeException("Can't parse episode " + file);
        }

        int episodeLength = extractEpisodeLength(file);
        return new Episode(
                file,
                name,
                index,
                episodeLength,
                series);
    }

    private int extractEpisodeLength(File file) {
        try {
            FFprobe ffProbe = Config.createFfprobe();
            FFmpegProbeResult result = ffProbe.probe(file.getAbsolutePath());
            return (int) result.getFormat().duration;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
