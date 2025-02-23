package com.engeto.plants;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

public class PlantFileWriter {
    private static final Logger logger = Logger.getLogger(PlantFileWriter.class.getName());
    private final String filename;
    private final String delimiter;

    public PlantFileWriter(String filename, String delimiter) {
        this.filename = filename;
        this.delimiter = delimiter;
    }

    public void writePlantsToFile(List<Plant> plants) {
        logger.info("Zapisuji rostliny do souboru: " + filename);
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Plant plant : plants) {
                exportPlant(plant, writer);
            }
            logger.info("Seznam rostlin byl úspěšně zapsán do souboru: " + filename + "\n");
        } catch (IOException e) {
            logger.severe("Chyba při zápisu do souboru " + filename + ": " + e.getLocalizedMessage());
        }
    }

    private void exportPlant(Plant plant, PrintWriter writer) {
        String output = plant.getName() + delimiter
                + plant.getNotes() + delimiter
                + plant.getFreqOfWatering() + delimiter
                + plant.getLastWateringDate() + delimiter
                + plant.getPlantedDate();
        writer.println(output);
    }
}
