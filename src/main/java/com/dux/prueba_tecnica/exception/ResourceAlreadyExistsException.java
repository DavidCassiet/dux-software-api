package com.dux.prueba_tecnica.exception;

import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistsException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final int code;
    private final String key;
    private final String field;

    public ResourceAlreadyExistsException(String key, String field) {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.code = httpStatus.value();
        this.key = key;
        this.field = field;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getCode() {
        return code;
    }

    public String getKey() {
        return key;
    }

    public String getField() {
        return field;
    }

    @Override
    public String getMessage() {
        return switch (key) {
            case "EXIST_DB" -> "El recurso '" + field + "' que desea crear ya existe";
            case "EXIST_DB_STATUS" -> "El recurso '" + field + "' que desea crear ya existe, pero está dado de baja";
            default -> "La solicitud es invalida";
        };
    }
}
