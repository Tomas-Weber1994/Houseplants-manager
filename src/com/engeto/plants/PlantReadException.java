package com.engeto.plants;

public class PlantReadException extends Exception {
    private final String filename;
    private final int lineNumber;
    private final String errorDetail;
    private final String invalidValue;

    public PlantReadException(String filename, int lineNumber, String errorDetail, String invalidValue) {
        super(errorDetail);
        this.filename = filename;
        this.lineNumber = lineNumber;
        this.errorDetail = errorDetail;
        this.invalidValue = invalidValue;
    }

    @Override
    public String toString() {
        return "Chyba při čtení souboru '" + filename + "' na řádku " + lineNumber +
                ": " + errorDetail + " (Neplatná hodnota: '" + invalidValue + "')";
    }
//    Possibility add getters for logging
}
