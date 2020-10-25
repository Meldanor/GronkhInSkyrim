package de.meldanor.gronkskyrim.ocr;

import de.meldanor.gronkskyrim.Config;
import de.meldanor.gronkskyrim.source.Episode;

import java.io.File;

public class Frame {

    private final Episode episode;
    private final int index;
    private final File frameFile;

    public Frame(Episode episode, File frameFile) {
        this.episode = episode;
        this.frameFile = frameFile;
        this.index = readIndex(frameFile);
    }

    private int readIndex(File frameFile) {
        String name = frameFile.getName();
        name = name.substring(0, name.indexOf('.'));
        return Integer.parseInt(name, 10);
    }

    public int episodeSecond() {
        return this.index * Config.OCR_FRAMES_PER_SECOND;
    }

    public Episode getEpisode() {
        return episode;
    }

    public int getIndex() {
        return index;
    }

    public File getFrameFile() {
        return frameFile;
    }
}
