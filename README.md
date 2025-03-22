# Application for Managing Houseplants

## Description
This is a basic application which is designed for managing houseplants. It allows you to maintain a list of plants, including adding, watering, removing, and modifying them, as well as loading and saving data from files. The application also logs operations to a file for easy traceability.

## Application Structure

### Main Classes:
- **Plant** – A class for modeling a plant, which contains information about the name, notes, planting date, last watering date, and watering frequency.
- **PlantManager** – A class for managing the plant list, which allows adding, removing, sorting, and watering plants. It calls the **PlantFileReader** class for loading and the **PlantFileWriter** class for writing plants to files.
- **PlantFileReader** – A class for reading plants from a TXT file. When reading a file, it automatically skips lines with errors and logs them to a file.
- **PlantFileWriter** – A class for writing plants to a TXT file.

## File Structure:
- **/resources** – A folder containing files with plant data, which are loaded when the application starts.
- **/logs** – A folder for the application's log files, which record operations and potential errors during program execution.

## Usage:
1. Plant loading and saving are handled by the **PlantFileReader** and **PlantFileWriter** classes.
2. The application automatically logs all operations (e.g., file reading, adding a new plant) to a file in the **/logs** folder.
3. To run the application, simply use the `main` method, which will load the plant list and perform the desired operations.
