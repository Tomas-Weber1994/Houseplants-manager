package com.engeto.plants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlantFileReader {
    private final String filename;
    private final String delimiter;

    public PlantFileReader(String filename, String delimiter) {
        this.filename = filename;
        this.delimiter = delimiter;
    }

    public List<Plant> parsePlantsFromFile() throws PlantReadException {
        List<Plant> plants = new ArrayList<>();
        int lineNumber = 0;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine();
                String[] values = line.split(delimiter);
                String name = values[0];
                String notes = values[1];
                int freqOfWatering = parseFreqOfWatering(values, lineNumber);
                LocalDate lastWateringDate = parseDate(values, 3, lineNumber);
                LocalDate plantDate = parseDate(values, 4, lineNumber);
                Plant plant = new Plant(name, notes, plantDate, lastWateringDate, freqOfWatering);
                plants.add(plant);
            }
        } catch (FileNotFoundException e) {
            throw new PlantReadException(
                    "Nepodařilo se najít soubor: " + filename + " " + e.getLocalizedMessage());
        } catch (InvalidPlantException e) {
            throw new PlantReadException(
                    "Chyba při načítání rostlin ze souboru: " + filename + " " + e.getLocalizedMessage()
            );
        }
        return plants;
    }

    private int parseFreqOfWatering(String[] values, int lineNumber) throws PlantReadException {
        try {
            return Integer.parseInt(values[2]);
        } catch (NumberFormatException e) {
            throw new PlantReadException(filename, lineNumber, "Špatně zadané číslo", values[2]);
        }
    }

    private LocalDate parseDate(String[] values, int index, int lineNumber) throws PlantReadException {
        try {
            return LocalDate.parse(values[index]);
        } catch (DateTimeParseException e) {
            throw new PlantReadException(filename, lineNumber, "Špatně zadané datum", values[index]);
        }
    }
}
