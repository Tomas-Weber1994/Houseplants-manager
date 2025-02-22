import com.engeto.plants.*;

import com.engeto.utils.LoggerConfig;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LoggerConfig.configureLogger();
        PlantManager plantManager = new PlantManager();

        try {
            plantManager.addPlantsFromFile(Constants.getFilename(), Constants.getDelimiter());
//            plantManager.addPlantsFromFile(Constants.getFilenameWrongDate(), Constants.getDelimiter());
//           plantManager.addPlantsFromFile(Constants.getFilenameWrongFreq(), Constants.getDelimiter());
//            plantManager.addPlantsFromFile("resources/kvetiny_mene_radku.txt", Constants.getDelimiter());
        } catch (PlantFileNotFoundException e) {
            logger.log(Level.SEVERE, "Špatný název souboru nebo cesta k němu! " + e.getMessage(), e);
    }
    }
}
