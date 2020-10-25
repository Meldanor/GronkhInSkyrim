package de.meldanor.gronkskyrim.preprocess;

import de.meldanor.gronkskyrim.Config;
import de.meldanor.gronkskyrim.source.Episode;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFmpegUtils;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.job.FFmpegJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class FrameExtractor {

    private static final Logger LOG = LoggerFactory.getLogger(FrameExtractor.class.getSimpleName());

    public File extractFrames(Episode episode) {
        LOG.info("Extracting frames of " + episode.toString());
        String episodeIndex = String.format("%04d", episode.getIndex());
        File episodeFrameDir = new File(Config.FRAMES_PATH, episodeIndex);
        episodeFrameDir.mkdirs();
        try {

            FFmpeg ffmpeg = Config.createFfmpeg();
            FFprobe ffprobe = Config.createFfprobe();
            FFmpegBuilder builder = ffmpeg.builder()
                    .addInput(episode.getFile().getAbsolutePath())
                    // THIS IS A BUG OF THE LIBRARY IN 0.6.2 - THE VALUES ARE SWAPPED
                    .setAudioFilter("fps=" + Config.OCR_FRAMES_PER_SECOND)
                    .addOutput(new File(episodeFrameDir, "%07d.jpg").getAbsolutePath())
                    .addExtraArgs("-qscale:v", "5")
                    .done();

            FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
            FFmpegJob job = executor.createJob(builder, progress ->
                    // Print out interesting information about the progress
                    LOG.info(String.format("status:%s frame:%d time:%s ms fps:%.0f speed:%.2fx",
                            progress.status,
                            progress.frame,
                            FFmpegUtils.toTimecode(progress.out_time_ns, TimeUnit.NANOSECONDS),
                            progress.fps.doubleValue(),
                            progress.speed))
            );
            job.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return episodeFrameDir;
    }


}
