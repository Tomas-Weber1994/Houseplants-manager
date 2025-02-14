package com.engeto.plants;

public class PlantReadException extends Exception {
    public PlantReadException(String filename, int lineNumber, String errorDetail, String invalidValue) {
        super("Chyba v souboru '" + filename + "' na řádku " + lineNumber +
                ": " + errorDetail + " (" + invalidValue + ")");
    }

    public PlantReadException(String message) {
        super(message);
    }
}
