package com.como.hogar.web.handlers;

import com.como.hogar.common.exceptions.BaseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleConstraintVliolationException(ConstraintViolationException ex) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleAutoException(BaseException ex) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String errorMessage = ex.getCause().getCause().getMessage();
        // Extraer el nombre de la restricción de clave foránea del mensaje de error
        String foreignKeyName = errorMessage.substring(errorMessage.indexOf("\"") + 1, errorMessage.lastIndexOf("\""));

        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("La operación no pudo ser completada debido a una restricción de integridad de datos. ");
        messageBuilder.append("Se violó la restricción de clave foránea ");
        messageBuilder.append(foreignKeyName);
        messageBuilder.append(".");

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(messageBuilder.toString());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(errorList.toString());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        String error = "El parámetro " + ex.getName() + " debe ser de tipo " + ex.getRequiredType().getSimpleName();
        apiError.setMessage(error);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> handleException(Exception ex) {
        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        final ApiError apiError = new ApiError(ex.getStatus());
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> notFoundException(final NullPointerException e) {
        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(Optional.of(e.getMessage()).orElse(e.getCause().getLocalizedMessage()));
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Object> assertionException(final ArrayIndexOutOfBoundsException e) {
        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(Optional.of(e.getMessage()).orElse(e.getCause().getLocalizedMessage()));
        return buildResponseEntity(apiError);
    }
}
