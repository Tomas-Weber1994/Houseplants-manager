import com.engeto.plants.*;
import com.engeto.utils.LoggerConfig;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LoggerConfig.configureLogger();
        logger.info("Program úspěšně spuštěn!");

        PlantManager plantManager = new PlantManager();
        loadPlantsFromFile(plantManager, Constants.getFilename(), Constants.getDelimiter());
        //        possibility to try instead another files containing errors from /resources
        plantManager.getWateringInfoForPlants();
        addDummyPlants(plantManager);
        plantManager.removePlant(2);
        plantManager.savePlantsToFile(Constants.getOutputFilename(), Constants.getDelimiter());
        //        trying to load the output file again
        plantManager.removeAllPlants();
        loadPlantsFromFile(plantManager, Constants.getOutputFilename(), Constants.getDelimiter());
        plantManager.sortPlantsByName();
        logger.info("Vypisuji seřazené rostliny dle jména: " + plantManager.getPlants());
        plantManager.sortPlantsByLastWateringDate();
        logger.info("Vypisuji seřazené rostliny dle data poslední zálivky: " + plantManager.getPlants());
        plantManager.getPlantsNeedingWater().forEach(Plant::doWateringNow);
    }

    private static void loadPlantsFromFile(PlantManager plantManager, String filename, String delimiter) {
        try {
            plantManager.addPlantsFromFile(filename, delimiter);
        } catch (PlantFileNotFoundException e) {
            logger.log(Level.SEVERE, "Špatný název souboru nebo cesta k němu! " + e.getMessage(), e);
        }
    }

    private static void addDummyPlants(PlantManager plantManager) {
        try {
            plantManager.addPlant(new Plant(
                    "Růže",
                    "s věnováním",
                    LocalDate.of(2025, 2, 1),
                    LocalDate.of(2025, 2, 8),
                    10));

            for (int i = 1; i < 11; i++) {
                plantManager.addPlant(new Plant("Tulipán na prodej " + i, 14));
            }
        } catch (InvalidPlantException e) {
            logger.log(Level.SEVERE, "Vytvoření rostliny či její úpravu nelze provést: " + e.getMessage(), e);
        }
    }
}
