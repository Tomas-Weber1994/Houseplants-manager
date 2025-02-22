package com.engeto.plants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlantFileReader {
    private static final Logger logger = Logger.getLogger(PlantFileReader.class.getName());
    private final String filename;
    private final String delimiter;

    public PlantFileReader(String filename, String delimiter) {
        this.filename = filename;
        this.delimiter = delimiter;
    }

    public List<Plant> parsePlantsFromFile() throws PlantReadException, PlantFileNotFoundException {
        List<Plant> plants = new ArrayList<>();
        int lineNumber = 0;

        logger.info("Načítám soubor: " + filename + "...");
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine();
                if (line.isEmpty()) continue;
                String[] values = line.split(delimiter);
                try {
                    Plant plant = parseLine(values, lineNumber);
                    plants.add(plant);
                } catch (InvalidPlantException e) {
                    throw new PlantReadException(filename, lineNumber, "Chyba při načítání rostliny", e.getMessage());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new PlantReadException(filename, lineNumber, "V souboru chybí některé hodnoty.", e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            throw new PlantFileNotFoundException("Soubor nenalezen!", filename);
        } finally {
            logParsingOutcome(plants);
        }
        return List.copyOf(plants);
    }

    private Plant parseLine(String[] values, int lineNumber) throws InvalidPlantException, PlantReadException {
        String name = values[0];
        return switch (values.length) {
            case 1 -> new Plant(name);
            case 2 -> new Plant(name, parseFreqOfWatering(values, lineNumber));
            default -> new Plant(
                    name,
                    values[1],
                    parseDate(values, 4, lineNumber),
                    parseDate(values, 3, lineNumber),
                    parseFreqOfWatering(values, lineNumber));
        };
    }

    private int parseFreqOfWatering(String[] values, int lineNumber) throws PlantReadException {
        try {
            return Integer.parseInt(values[2]);
        } catch (NumberFormatException e) {
            throw new PlantReadException(filename, lineNumber, "Špatně zadané číslo!", values[2]);
        }
    }

    private LocalDate parseDate(String[] values, int index, int lineNumber) throws PlantReadException {
        try {
            return LocalDate.parse(values[index]);
        } catch (DateTimeParseException e) {
            throw new PlantReadException(filename, lineNumber, "Špatně zadané datum!", values[index]);
        }
    }

    private void logParsingOutcome(List<Plant> parsedPlants) {
        if (parsedPlants.isEmpty()) {
            logger.log(Level.WARNING,
                    "Ze souboru se nepodařilo načíst žádné rostliny, nicméně program může klidně pokračovat dál...");
        } else {
            logger.info("Ze souboru byly načteny následující rostliny: " + parsedPlants.toString());
        }
    }
}
