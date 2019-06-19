package com.mulecode.jwtserver.resource;

import com.mulecode.jwtserver.client.ClientRegistrationException;
import com.mulecode.jwtserver.resource.model.ErrorObject;
import com.mulecode.jwtserver.resource.model.ResponseError;
import com.mulecode.jwtserver.user.UserDetailsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@ControllerAdvice
public class AdviceResource {

    static final Logger LOGGER = LoggerFactory.getLogger(AdviceResource.class);

    @ResponseBody
    @ExceptionHandler(UserDetailsException.class)
    public ResponseEntity userDetailsException(UserDetailsException e, HttpServletRequest request) {

        LOGGER.debug(e.getMessage());

        HttpStatus status = HttpStatus.UNAUTHORIZED;

        final Map<String, Object> responseBody = new ResponseError.Builder()
                .message(e.getMessage())
                .status(status.value())
                .error(status.getReasonPhrase())
                .requestURI(request.getRequestURI())
                .build().get();

        return ResponseEntity.status(status).body(responseBody);
    }

    @ResponseBody
    @ExceptionHandler(ClientRegistrationException.class)
    public ResponseEntity clientRegistrationException(ClientRegistrationException e, HttpServletRequest request) {

        LOGGER.debug(e.getMessage());

        HttpStatus status = HttpStatus.UNAUTHORIZED;

        final Map<String, Object> responseBody = new ResponseError.Builder()
                .message(e.getMessage())
                .status(status.value())
                .error(status.getReasonPhrase())
                .requestURI(request.getRequestURI())
                .build().get();

        return ResponseEntity.status(status).body(responseBody);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity noHandlerFoundException(Exception e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        LOGGER.error(e.getMessage(), e);

        final Map<String, Object> responseBody = new ResponseError.Builder()
                .message(e.getMessage())
                .status(status.value())
                .error(status.getReasonPhrase())
                .requestURI(request.getRequestURI())
                .build().get();

        return ResponseEntity.status(status).body(responseBody);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity argumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {

        final int sizeErrors = e.getBindingResult().getFieldErrors().size();

        final Set<ErrorObject> collect = e.getBindingResult().getFieldErrors().stream()
                .map(error -> ErrorObject.create(error.getField(), error.getCode()))
                .collect(Collectors.toSet());

        final Map<String, Object> responseBody = new ResponseError.Builder()
                .message("Validation failed. Error count: " + sizeErrors)
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(collect)
                .requestURI(request.getRequestURI())
                .build()
                .get();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
