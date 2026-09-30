package com.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada para entradas inválidas en la aplicación.
 * Se utiliza cuando los datos proporcionados por el cliente no cumplen
 * con las validaciones de negocio o las restricciones definidas.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException {

    private final String field;
    private final Object rejectedValue;

    public InvalidInputException(String message) {
        super(message);
        this.field = null;
        this.rejectedValue = null;
    }

    public InvalidInputException(String message, String field) {
        super(message);
        this.field = field;
        this.rejectedValue = null;
    }

    public InvalidInputException(String message, String field, Object rejectedValue) {
        super(message);
        this.field = field;
        this.rejectedValue = rejectedValue;
    }

    public String getField() {
        return field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    @Override
    public String toString() {
        if (field != null && rejectedValue != null) {
            return String.format("InvalidInputException: %s [field=%s, rejectedValue=%s]", 
                getMessage(), field, rejectedValue);
        } else if (field != null) {
            return String.format("InvalidInputException: %s [field=%s]", getMessage(), field);
        }
        return super.toString();
    }
}