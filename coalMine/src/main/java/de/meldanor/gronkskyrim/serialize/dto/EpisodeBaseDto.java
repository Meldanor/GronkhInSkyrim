package de.meldanor.gronkskyrim.serialize.dto;

import de.meldanor.gronkskyrim.data.EpisodeBase;

public class EpisodeBaseDto {
    private String name;
    private int index;
    private int lengthSeconds;

    public EpisodeBaseDto() {
    }

    public EpisodeBaseDto(EpisodeBase original) {
        this.name = original.getName();
        this.index = original.getIndex();
        this.lengthSeconds = original.getLengthSeconds();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getLengthSeconds() {
        return lengthSeconds;
    }

    public void setLengthSeconds(int lengthSeconds) {
        this.lengthSeconds = lengthSeconds;
    }
}
