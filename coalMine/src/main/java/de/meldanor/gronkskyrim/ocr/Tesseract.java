package de.meldanor.gronkskyrim.ocr;

import de.meldanor.gronkskyrim.Util;

import java.io.File;

import static de.meldanor.gronkskyrim.Config.TESSERACT_PATH;

public class Tesseract {

    public static final int PAGE_SEGMENTATION_MODE_SINGLE_LINE = 7;

    private static final Tesseract instance = new Tesseract();

    public static Tesseract instance() {
        return instance;
    }

    private final String path;

    private Tesseract() {
        this.path = TESSERACT_PATH.getAbsolutePath();
    }

    public String extractText(File file) {
        return extractText(file, 1);
    }

    public String extractText(File file, int pageSegmentationMode) {
        ProcessBuilder builder = new ProcessBuilder(
                this.path,
                "stdin",
                "stdout",
                "quiet",
                "-l",
                "deu",
                "--psm",
                Integer.toString(pageSegmentationMode)

        )
                .redirectErrorStream(true)
                .redirectInput(file);
        builder.environment().put("LANG", "de_DE.UTF-8");
        try {
            Process pr = builder.start();
            return Util.readProcessOutput(pr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
