package de.meldanor.gronkskyrim.ocr;

public class ParseException extends RuntimeException {
    private final String ocrText;

    public ParseException(String ocrText) {
        super("Can't parse OCR text '" + ocrText + "'");
        this.ocrText = ocrText;
    }

    public String getOcrText() {
        return ocrText;
    }
}
