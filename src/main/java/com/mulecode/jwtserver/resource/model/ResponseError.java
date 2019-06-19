package com.mulecode.jwtserver.resource.model;

import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by rbroz on 27/07/2017.
 */
public class ResponseError {

    private final static String RES_TIMESTAMP = "timestamp";
    private final static String RES_STATUS = "status";
    private final static String RES_ERROR = "error";
    private final static String RES_ERRORS = "errors";
    private final static String RES_MESSAGE = "message";
    private final static String RES_EXCEPTION = "exception";
    private final static String RES_PATH = "path";


    private String message;
    private Exception exception;
    private String requestURI;
    private Integer status;
    private String error;
    private Collection<ErrorObject> errors;

    public ResponseError() {

    }

    public ResponseError(Builder builder) {
        this.status = builder.status;
        this.error = builder.error;
        this.errors = builder.errors;
        this.message = builder.message;
        this.exception = builder.exception;
        this.requestURI = builder.requestURI;
    }

    public Map<String, Object> get() throws RuntimeException {
        Map<String, Object> responseBody = new LinkedHashMap<>();

        responseBody.put(RES_TIMESTAMP, Instant.now().toEpochMilli());

        this.status = Optional.ofNullable(this.status).orElse(500);
        responseBody.put(RES_STATUS, this.status);

        if(StringUtils.isNotBlank(this.error)) {
            responseBody.put(RES_ERROR, this.error);
        }

        if(this.errors != null && !this.errors.isEmpty()){
            responseBody.put(RES_ERRORS, this.errors);
        }

        if (StringUtils.isBlank(this.message) && this.exception != null) {
            this.message = exception.getMessage();
        }
        responseBody.put(RES_MESSAGE, this.message);

        if (this.exception != null) {
            responseBody.put(RES_EXCEPTION, this.exception.getMessage());
        }

        if (StringUtils.isNotBlank(this.requestURI)) {
            responseBody.put(RES_PATH, requestURI);
        }

        return responseBody;
    }

    public static class Builder {

        private String message;
        private Integer status;
        private String error;
        private Exception exception;
        private String requestURI;
        private Collection<ErrorObject> errors;

        public ResponseError.Builder message(final String message) {
            this.message = message;
            return this;
        }

        public ResponseError.Builder status(final Integer status) {
            this.status = status;
            return this;
        }

        public ResponseError.Builder error(final String error) {
            this.error = error;
            return this;
        }

        public ResponseError.Builder exception(final Exception exception) {
            this.exception = exception;
            return this;
        }

        public ResponseError.Builder requestURI(final String requestURI) {
            this.requestURI = requestURI;
            return this;
        }

        public Builder errors(Collection<ErrorObject> errors) {
            this.errors = errors;
            return this;
        }

        public ResponseError build() {
            return new ResponseError(this);
        }


    }
}
