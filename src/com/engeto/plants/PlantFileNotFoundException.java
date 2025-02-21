package com.engeto.plants;

public class PlantFileNotFoundException extends Exception {
    public String filename;

    public PlantFileNotFoundException(String message, String filename) {
        super(message);
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "Nepodařilo se najít soubor: " + filename;
    }
}
