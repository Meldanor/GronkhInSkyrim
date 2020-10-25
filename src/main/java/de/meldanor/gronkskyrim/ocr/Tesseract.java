package de.meldanor.gronkskyrim.ocr;

import de.meldanor.gronkskyrim.Util;

import java.io.File;

import static de.meldanor.gronkskyrim.Config.TESSERACT_PATH;

public class Tesseract {
    private static final Tesseract instance = new Tesseract();

    public static Tesseract instance() {
        return instance;
    }

    private final String path;

    private Tesseract() {
        this.path = TESSERACT_PATH.getAbsolutePath();
    }

    public String extractText(File file) {
        ProcessBuilder builder = new ProcessBuilder(
                this.path,
                file.getAbsolutePath(),
                "stdout",
                "quiet",
                "-l",
                "deu"

        ).redirectErrorStream(true);
        builder.environment().put("LANG", "de_DE.UTF-8");
        try {
            Process pr = builder.start();
            return Util.readProcessOutput(pr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
