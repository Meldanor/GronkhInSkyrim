package de.meldanor.gronkskyrim.ocr;

import de.meldanor.gronkskyrim.data.PlayerWeight;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Optional;

public class WeightExtractor {

    public Optional<PlayerWeight> extract(Frame frame) throws Exception {
        File weightImage = extractWeightPart(frame);
        String s = Tesseract.instance().extractText(weightImage);
        if (s != null && s.contains("Traglast")) {
            return parseWeight(s);
        }
        return Optional.empty();
    }

    private File extractWeightPart(Frame frame) throws Exception {
        File frameFile = frame.getFrameFile();
        BufferedImage image = ImageIO.read(frameFile);
        BufferedImage dest = image.getSubimage(1400, 998, 184, 42);
        File f = File.createTempFile(frameFile.getName(), ".png");
        ImageIO.write(dest, "png", f);
        return f;
    }

    private Optional<PlayerWeight> parseWeight(String s) {
        try {
            return Optional.of(new PlayerWeight(s.replace("Traglast", "")));
        } catch (Exception e) {
            return Optional.empty();
        }

    }

}
