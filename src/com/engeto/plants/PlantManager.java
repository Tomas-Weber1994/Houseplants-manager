package com.engeto.plants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlantManager {
    List<Plant> plants = new ArrayList<>();

    public void addPlant(Plant newPlant) {
        plants.add(newPlant);
    }

    public void removePlant(int index) {
        plants.remove(index);
    }

    public List<Plant> getPlants() {
        return new ArrayList<>(plants);
    }

    public void replacePlantList(List<Plant> newPlants) {
        plants = new ArrayList<>(newPlants);
    }

    public Plant getPlantAtIndex(int index) {
        return plants.get(index);
    }

    public List<Plant> getPlantsNeedingWater() {
        return plants
                .stream()
                .filter(plant -> plant.getNextWateringDate().isAfter(LocalDate.now()))
                .toList();
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
}
