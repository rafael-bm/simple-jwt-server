package com.mulecode.jwtserver.resource.model;

public class ErrorObject {

    private String field;
    private String message;

    private ErrorObject() {
    }

    public static ErrorObject create(final String field, final String message) {
        ErrorObject e = new ErrorObject();
        e.setField(field);
        e.setMessage(message);
        return e;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}
