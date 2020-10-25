package de.meldanor.gronkskyrim.source;

import java.io.File;

public final class Episode {

    private final File file;
    private final String name;
    private final int index;
    private final int lengthSeconds;

    Episode(File file, String name, int index, int lengthSeconds) {
        this.file = file;
        this.name = name;
        this.index = index;
        this.lengthSeconds = lengthSeconds;
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
