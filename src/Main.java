import com.engeto.plants.*;
import com.engeto.utils.LoggerConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LoggerConfig.configureLogger();
        PlantManager plants = new PlantManager();
        try {
            logger.info("Aplikace úspěšně spuštěna!");
            Plant plant = new Plant("Tulipán", 10);
            logger.info("Založena květina: " + plant.getName());
            logger.info("Informace o zalévání: " + plant.getWateringInfo());
            logger.info("Další termín zalévání: " + plant.getNextWateringDate());

             plants.addPlantsFromFile(Constants.getFilename(), Constants.getDelimiter());
//            plants.addPlantsFromFile(Constants.getFilenameWrongDate(), Constants.getDelimiter());
            // plants.addPlantsFromFile(Constants.getFilenameWrongFreq(), Constants.getDelimiter());
            logger.info("Načtení rostlin ze souboru " + Constants.getFilename() + " proběhlo úspěšně.");
        } catch (InvalidPlantException e) {
            logger.log(Level.SEVERE, "Vytvoření rostliny či její úpravu nelze provést: " + e.getMessage(), e);
        } catch (PlantFileNotFoundException | PlantReadException e) {
            logger.log(Level.SEVERE, "Chyba při čtení ze souboru: " + e.getMessage(), e);
        }
        if (plants.getPlants().isEmpty()) {
            logger.log(Level.WARNING, "Seznam rostlin je prázdný, ale program může klidně pokračovat dál...");
        } else {
            logger.info("Aktuálně eviduji následující rostliny: " + plants.getPlants());
        }
    }
}
