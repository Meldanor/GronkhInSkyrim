package de.meldanor.gronkskyrim.postprocess;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsedEpisodeFactory {
    private static final ParsedEpisodeFactory instance = new ParsedEpisodeFactory();

    public static ParsedEpisodeFactory instance() {
        return instance;
    }

    private static final Pattern EPISODE_NAME_PATTERN = Pattern.compile("(\\d{4})__(\\d*)s__(.*).txt");

    private ParsedEpisodeFactory() {
    }


    public ParsedEpisode createEpisode(ParsedSeries series, File file) {
        String fileName = file.getName();
        Matcher matcher = EPISODE_NAME_PATTERN.matcher(fileName);
        String name;
        int index;
        int episodeLength;
        if (matcher.find()) {
            index = Integer.parseInt(matcher.group(1));
            episodeLength = Integer.parseInt(matcher.group(2));
            name = matcher.group(3);
        } else {
            throw new RuntimeException("Can't parse episode " + file);
        }

        return new ParsedEpisode(
                name,
                index,
                episodeLength,
                series);
    }
}
