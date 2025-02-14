package com.engeto.plants;

public class PlantReadException extends Exception {
    private String invalidField;
    private Object invalidValue;

    public PlantReadException(String message) {
        super(message);
    }

    public PlantReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlantReadException(String message, String invalidField, Object invalidValue) {
        super(message);
        this.invalidField = invalidField;
        this.invalidValue = invalidValue;
    }

    @Override
    public String toString() {
        return super.toString() +
                (invalidField != null ? " [Atribut: " + invalidField + ", Hodnota: " + invalidValue + "]" : "");
    }
}
