package com.engeto.plants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class PlantManager {
    private static final Logger logger = Logger.getLogger(PlantManager.class.getName());
    List<Plant> plants = new ArrayList<>();

    public void addPlant(Plant newPlant) {
        plants.add(newPlant);
        logger.info("Přidána rostlina: " + newPlant);
    }

    public void removePlant(int index) {
        Plant plantOnIndex = plants.get(index);
        plants.remove(index);
        logger.info("Odebrána rostlina: " + plantOnIndex);
    }

    public void removeAllPlants() {
        plants.clear();
        logger.warning("Byly odstraněny všechny rostliny!");
    }

    public List<Plant> getPlants() {
        return new ArrayList<>(plants);
    }

    public void replacePlantList(List<Plant> newPlants) {
        plants = new ArrayList<>(newPlants);
        logger.warning("Seznam rostlin byl nahrazen!");
    }

    public List<Plant> getPlantsNeedingWater() {
        return plants
                .stream()
                .filter(plant -> plant.getNextWateringDate().isBefore(LocalDate.now()))
                .toList();
    }

    public void getWateringInfoForPlants() {
        for (Plant plant : plants) {
            logger.info("Vypisuji informaci o zalévání pro rostlinu: " + plant + ": "
                    + plant.getWateringInfo());
        }
    }

    // Default sorting per name
    public void sortPlantsByName() {
        plants.sort(null);
    }

    public void sortPlantsByLastWateringDate() {
        plants.sort(Comparator.comparing(Plant::getLastWateringDate));
    }

    public void addPlantsFromFile(String filename, String delimiter) throws PlantFileNotFoundException {
        PlantFileReader plantFileReader = new PlantFileReader(filename, delimiter);
        List<Plant> newPlants = plantFileReader.parsePlantsFromFile();
        plants.addAll(newPlants);
    }

    public void savePlantsToFile(String filename, String delimiter) {
        PlantFileWriter plantFileWriter = new PlantFileWriter(filename, delimiter);
        plantFileWriter.writePlantsToFile(plants);
    }
}
