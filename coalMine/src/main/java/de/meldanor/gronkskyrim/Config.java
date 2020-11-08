package de.meldanor.gronkskyrim;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;

import java.io.File;

public class Config {

    private final static File FFMPEG_PATH = new File("/usr/bin/ffmpeg");

    public static FFmpeg createFfmpeg() throws Exception {
        return new FFmpeg(FFMPEG_PATH.getAbsolutePath());
    }

    public final static File FFPROBE_PATH = new File("/usr/bin/ffprobe");

    public static FFprobe createFfprobe() throws Exception {
        return new FFprobe(FFPROBE_PATH.getAbsolutePath());
    }

    public final static File TESSERACT_PATH = new File("/usr/bin/tesseract");
    // The FPS of the OCR process - the more frames, there more data
    public static final int OCR_FRAMES_PER_SECOND = 2;

    // The path to the original videos
    public static final File VIDEOS_PATH = new File("/videos");
    // The path to the frames extracted from the videos
    public static final File FRAMES_PATH = new File("/frames");
    // An output path where the event log files are written to
    public static final File EVENT_LOG_PATH = new File("/eventLogs");
    // A 
    public static final File POST_PROCESS_SERIES_LOG_PATH = new File("/Users/kiliangartner/Downloads/20201027__231807__300__669769");
}
