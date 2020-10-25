package de.meldanor.gronkskyrim.source;

import java.io.File;

public final class Episode {

    private final File file;
    private final String name;
    private final int index;
    private final int lengthSeconds;
    private final Series series;

    Episode(File file, String name, int index, int lengthSeconds, Series series) {
        this.file = file;
        this.name = name;
        this.index = index;
        this.lengthSeconds = lengthSeconds;
        this.series = series;
    }

    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public int getLengthSeconds() {
        return lengthSeconds;
    }

    public Series getSeries() {
        return series;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "file=" + file +
                ", name='" + name + '\'' +
                ", index=" + index +
                ", lengthSeconds=" + lengthSeconds +
                '}';
    }
}
