package com.engeto.plants;

public class InvalidPlantException extends Exception {
    private String invalidField;
    private Object invalidValue;

    public InvalidPlantException(String message) {
        super(message);
    }

    public InvalidPlantException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPlantException(String message, String invalidField, Object invalidValue) {
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
