package com.engeto.plants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlantManager {
    List<Plant> plants = new ArrayList<>();

    public PlantManager(List<Plant> plants) {
        this.plants.addAll(plants);
    }

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
        List<Plant> plantsNeedingWater = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        for (Plant plant : plants) {
            if (!plant.getNextWateringDate().isAfter(currentDate)) {
                plantsNeedingWater.add(plant);
            }
        }
        return plantsNeedingWater;
    }
}
