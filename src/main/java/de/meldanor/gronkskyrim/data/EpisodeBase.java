package de.meldanor.gronkskyrim.data;

public abstract class EpisodeBase {
    protected final String name;
    protected final int index;
    protected final int lengthSeconds;
    protected final SeriesBase<? extends EpisodeBase> series;

    protected EpisodeBase(String name, int index, int lengthSeconds, SeriesBase<? extends EpisodeBase> series) {
        this.name = name;
        this.index = index;
        this.lengthSeconds = lengthSeconds;
        this.series = series;
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

    public SeriesBase<? extends EpisodeBase> getSeries() {
        return series;
    }

    @Override
    public String toString() {
        return "EpisodeBase{" +
                "name='" + name + '\'' +
                ", index=" + index +
                ", lengthSeconds=" + lengthSeconds +
                ", series=" + series +
                '}';
    }
}
