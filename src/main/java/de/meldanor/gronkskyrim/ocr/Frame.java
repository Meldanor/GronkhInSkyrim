package de.meldanor.gronkskyrim.ocr;

import de.meldanor.gronkskyrim.Config;
import de.meldanor.gronkskyrim.source.Episode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
        return this.index / Config.OCR_FRAMES_PER_SECOND;
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

//    public InputStream clipFrame(int x, int y, int width, int height) throws Exception {
//        BufferedImage image = ImageIO.read(this.getFrameFile());
//        BufferedImage subimage = image.getSubimage(x, y, width, height);
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        ImageIO.write(subimage, "png", stream);
//        stream.flush();
//        return new ByteArrayInputStream(stream.toByteArray());
//    }

    public File clipFrame(int x, int y, int width, int height, File temporaryFolder) throws Exception {
        BufferedImage image = ImageIO.read(this.getFrameFile());
        BufferedImage subimage = image.getSubimage(x, y, width, height);
        File file = File.createTempFile(this.episodeSecond() + "_playerweight", ".png", temporaryFolder);
        ImageIO.write(subimage, "png", file);
        return file;
    }
}
