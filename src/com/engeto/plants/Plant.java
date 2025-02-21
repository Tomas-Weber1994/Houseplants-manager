package com.engeto.plants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Plant implements Comparable<Plant> {
    private String name;
    private String notes;
    private LocalDate plantDate;
    private LocalDate lastWateringDate;
    private int freqOfWatering;

    public Plant(String name, String notes, LocalDate plantDate, LocalDate lastWateringDate, int freqOfWatering) throws InvalidPlantException {
        this.name = name;
        this.notes = notes;
        this.plantDate = plantDate;
        this.lastWateringDate = lastWateringDate;
        this.freqOfWatering = freqOfWatering;
        checkPositiveFrequency();
        validateLastWateringDate();
    }

    public Plant(String name, int freqOfWatering) throws InvalidPlantException {
        this(name, "", LocalDate.now(), LocalDate.now(), freqOfWatering);
    }

    public Plant(String name) throws InvalidPlantException {
        this(name, "", LocalDate.now(), LocalDate.now(), 7);
    }

    public String getWateringInfo() {
        return "\nDatum poslední zálivky: " +
                formatDate(lastWateringDate) + "\nDoporučené datum příští zálivky: " +
                formatDate(getNextWateringDate());
    }

    public void doWateringNow() {
        lastWateringDate = LocalDate.now();
    }

    public LocalDate getNextWateringDate() {
        return lastWateringDate.plusDays(freqOfWatering);
    }

    private String formatDate(LocalDate date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(dateFormatter);
    }

    private void checkPositiveFrequency() throws InvalidPlantException {
        if (freqOfWatering <= 0) {
            throw new InvalidPlantException(
                    "Frekvence zalévání musí být větší než 0!",
                    "freqOfWatering",
                    freqOfWatering
            );
        }
    }

    private void validateLastWateringDate() throws InvalidPlantException {
        if (lastWateringDate.isBefore(plantDate)) {
            throw new InvalidPlantException("Datum poslední zálivky nesmí být starší než datum vysazení rostliny!");
        }
    }

    // region getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlantDate() {
        return plantDate;
    }

    public void setPlantDate(LocalDate plantDate) {
        this.plantDate = plantDate;
    }

    public LocalDate getLastWateringDate() {
        return lastWateringDate;
    }

    public void setLastWateringDate(LocalDate lastWateringDate) throws InvalidPlantException {
        this.lastWateringDate = lastWateringDate;
        validateLastWateringDate();
    }

    public int getFreqOfWatering() {
        return freqOfWatering;
    }

    public void setFreqOfWatering(int freqOfWatering) throws InvalidPlantException {
        this.freqOfWatering = freqOfWatering;
        checkPositiveFrequency();
    }

    // endregion
    @Override
    public int compareTo(Plant other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return notes.isEmpty() ? name : name + " (" + notes + ")";
    }
}