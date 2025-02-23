package com.engeto.plants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class PlantFileReader {
    private static final Logger logger = Logger.getLogger(PlantFileReader.class.getName());
    private final String filename;
    private final String delimiter;
    private final List<PlantReadException> errorList = new ArrayList<>();

    public PlantFileReader(String filename, String delimiter) {
        this.filename = filename;
        this.delimiter = delimiter;
    }

    public List<Plant> parsePlantsFromFile() throws PlantFileNotFoundException {
        List<Plant> plants = new ArrayList<>();
        int lineNumber = 0;
        logger.info("Načítám soubor: " + filename + "...");

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine();
                if (line.isBlank()) continue;
                String[] values = line.split(delimiter);
                try {
                    Plant plant = parseLine(values, lineNumber);
                    plants.add(plant);
                } catch (InvalidPlantException e) {
                    errorList.add(new PlantReadException(filename, lineNumber, "Chyba při zpracování rostliny!", e.getMessage()));
                } catch (ArrayIndexOutOfBoundsException e) {
                    errorList.add(new PlantReadException(filename, lineNumber, "Nedostatek hodnot v řádku!", line));
                } catch (PlantReadException e) {
                    errorList.add(new PlantReadException(filename, lineNumber, e.getErrorDetail(), e.getInvalidValue()));
                }
            }
        } catch (FileNotFoundException e) {
            throw new PlantFileNotFoundException("Soubor nenalezen!", filename);
        }
        logError();
        logParsedPlants(plants);
        return List.copyOf(plants);
    }

    private Plant parseLine(String[] values, int lineNumber) throws InvalidPlantException, PlantReadException {
        String name = values[0];
        return switch (values.length) {
            case 1 -> new Plant(name);
            case 2 -> new Plant(name, parseFreqOfWatering(values[1], lineNumber));
            default -> new Plant(
                    name,
                    values[1],
                    parseDate(values[4], lineNumber),
                    parseDate(values[3], lineNumber),
                    parseFreqOfWatering(values[2], lineNumber));
        };
    }

    private int parseFreqOfWatering(String value, int lineNumber) throws PlantReadException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new PlantReadException(filename, lineNumber, "Neplatné číslo!", value);
        }
    }

    private LocalDate parseDate(String value, int lineNumber) throws PlantReadException {
        try {
            return LocalDate.parse(value);
        } catch (DateTimeParseException e) {
            throw new PlantReadException(filename, lineNumber, "Neplatné datum!", value);
        }
    }

    private void logParsedPlants(List<Plant> parsedPlants) {
        if (parsedPlants.isEmpty()) {
            logger.severe("Ze souboru se nepodařilo načíst žádné rostliny.\n");
        } else {
            logger.info("Načtené rostliny ze souboru: " + parsedPlants + "\n");
        }
    }

    private void logError() {
        if (!errorList.isEmpty()) {
            for (PlantReadException error : errorList) {
                logger.warning(
                        " - Řádek "
                                + error.getLineNumber() + " přeskočen: "
                                + error.getErrorDetail() + " ("
                                + error.getInvalidValue() + ")");
            }
        }
    }
}
