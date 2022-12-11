package org.example.exceptions;

public class SecurityConfigurationException extends RuntimeException {
    public SecurityConfigurationException(String message) {
        super("During configuration an error occurred: " + message);
    }
}
