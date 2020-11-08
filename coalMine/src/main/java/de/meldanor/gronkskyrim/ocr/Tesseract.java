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
                "stdin",
                "stdout",
                "quiet",
                "-l",
                "deu"

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

//    public String extractText(InputStream streamWithPicture) {
//        ProcessBuilder builder = new ProcessBuilder(
//                this.path,
//                "stdin",
//                "stdout",
//                "quiet",
//                "-l",
//                "deu"
//        ).redirectErrorStream(true);
//        builder.environment().put("LANG", "de_DE.UTF-8");
//        builder.redirectInput()
//        try {
//            Process pr = builder.start();
//            System.out.println("lol1");
//            streamWithPicture.transferTo(pr.getOutputStream());
//            streamWithPicture.close();
//            System.out.println("lol2");
//            return Util.readProcessOutput(pr);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
