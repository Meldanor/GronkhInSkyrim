package de.meldanor.gronkskyrim.ocr;

import de.meldanor.gronkskyrim.data.EpisodeBase;
import de.meldanor.gronkskyrim.data.EpisodeMoment;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Frame extends EpisodeMoment {

    private final File frameFile;

    public Frame(EpisodeBase episode, File frameFile) {
        super(episode, readIndex(frameFile));
        this.frameFile = frameFile;
    }

    private static int readIndex(File frameFile) {
        String name = frameFile.getName();
        name = name.substring(0, name.indexOf('.'));
        return Integer.parseInt(name, 10);
    }

    public File getFrameFile() {
        return frameFile;
    }


    public File clipFrame(int x, int y, int width, int height, File temporaryFolder) throws Exception {
        BufferedImage image = ImageIO.read(this.getFrameFile());
        BufferedImage subimage = image.getSubimage(x, y, width, height);
        File file = File.createTempFile(this.episodeSecond() + "_playerweight", ".png", temporaryFolder);
        ImageIO.write(subimage, "png", file);
        return file;
    }
}
