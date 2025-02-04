package kz.nik.sensorspostgswagkeycloack.exceptions;

public class SensorAlreadyExistsException extends RuntimeException {
    public SensorAlreadyExistsException(String message) {
        super(message);
    }
}