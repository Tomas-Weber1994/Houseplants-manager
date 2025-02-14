import com.engeto.plants.*;

public class Main {
    public static void main(String[] args) {
        try {
            Plant plant = new Plant("Tulipán", 10);
            System.out.println(plant.getWateringInfo());
            System.out.println(plant.getNextWateringDate());
            PlantManager plants = new PlantManager();
            plants.addPlantsFromFile(Constants.FILENAME, Constants.DELIMITER);
            System.out.println(plants.getPlants().size());
        } catch (InvalidPlantException e) {
            e.printStackTrace();
            System.err.println("Vytvoření rostliny či její úpravu nelze provést: " + e.getMessage());
        } catch (PlantReadException e) {
            e.printStackTrace();
            System.err.println("Chyba při čtení ze souboru: " + e.getMessage());
        }
    }
}