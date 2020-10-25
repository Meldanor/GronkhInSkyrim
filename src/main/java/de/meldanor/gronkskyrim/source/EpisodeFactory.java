package de.meldanor.gronkskyrim.source;

import de.meldanor.gronkskyrim.Application;
import de.meldanor.gronkskyrim.Util;

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

    public Episode createEpisode(File file) {
        String fileName = file.getName();
        Matcher matcher = EPISODE_NAME_PATTERN.matcher(fileName);
        String name = "";
        int index = 0;
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
                episodeLength
        );
    }

    private int extractEpisodeLength(File file) {
        ProcessBuilder builder = new ProcessBuilder(
                Application.FFPROBE_PATH.getAbsolutePath(),
                "-v",
                "error",
                "-show_entries",
                "format=duration",
                "-of",
                "default=noprint_wrappers=1:nokey=1",
                file.getAbsolutePath()

        ).redirectErrorStream(true);
        builder.environment().put("LANG", "de_DE.UTF-8");
        try {
            Process pr = builder.start();
            String s = Util.readProcessOutput(pr);
            return (int) Double.parseDouble(s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
