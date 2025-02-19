import com.engeto.plants.*;

public class Main {
    public static void main(String[] args) {
        PlantManager plants = new PlantManager();
        try {
            Plant plant = new Plant("Tulipán", 10);
            System.out.println(plant.getWateringInfo());
            System.out.println(plant.getNextWateringDate());
//            plants.addPlantsFromFile(Constants.FILENAME, Constants.DELIMITER);
//            plants.addPlantsFromFile(Constants.FILENAME_WRONG_FREQ, Constants.DELIMITER);
//            plants.addPlantsFromFile(Constants.FILENAME_WRONG_DATE, Constants.DELIMITER);
            plants.addPlantsFromFile("resources/kvetiny_mene_radku.txt", Constants.DELIMITER);
            System.out.println(plants.getPlants().size());
        } catch (InvalidPlantException e) {
            e.printStackTrace();
            System.err.println("Vytvoření rostliny či její úpravu nelze provést: " + e.getMessage());
        } catch (PlantFileNotFoundException | PlantReadException e) {
            e.printStackTrace();
            System.err.println("Chyba při čtení ze souboru: " + e.getMessage());
        }
        if (plants.getPlants().isEmpty()) {
            System.out.println("Seznam rostlin je prázdný, ale program může klidně pokračovat dál...");
        } else {
            System.out.println("Aktuálně eviduji následující rostliny: " + plants.getPlants());
        }
    }
}