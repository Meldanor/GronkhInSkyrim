package de.meldanor.gronkskyrim;

import java.io.File;

public class Config {

    public final static File FFMPEG_PATH = new File("/usr/bin/ffmpeg");
    public final static File FFPROBE_PATH = new File("/usr/bin/ffprobe");
    public final static File TESSERACT_PATH = new File("/usr/bin/tesseract");
    // The FPS of the OCR process - the more frames, there more data
    public static final int OCR_FRAMES_PER_SECOND = 10;

    public static final File VIDEOS_PATH = new File("/videos");
    public static final File FRAMES_PATH = new File("/frames");
}
