package com.como.hogar.common.util;

import com.como.hogar.web.dtos.ClienteTo;
import com.como.hogar.web.exceptions.ParametroEntradaException;

import java.util.ArrayList;

public final class ClientValidationUtil {
    private ClientValidationUtil() {
        throw new AssertionError();
    }

    public static <T> void checkNull(final T resource) {
        if (resource == null) {
            throw new ParametroEntradaException("El parámetro de entrada no puede ser nulo.");
        }
    }

    public static <T> void checkEmptyString(final T resource, final String fieldName) {
        checkNull(resource);

        if (resource instanceof String) {
            if (((String) resource).trim().isEmpty()) {
                throw new ParametroEntradaException(String.format("El campo '%s' no puede estar vacío.", fieldName));
            }
        }
    }

    public static <T extends CharSequence> void checkLength(final T resource, final int maxLength, final String fieldName) {
        checkNull(resource);

        if (resource.length() > maxLength) {
            throw new ParametroEntradaException(String.format("El campo '%s' no puede tener más de %d caracteres.", fieldName, maxLength));
        }
    }

    public static void checkValidClienteTo(ClienteTo clienteTo) {
        checkNull(clienteTo);
        checkEmptyString(clienteTo.getNombre(), "nombre");
        checkLength(clienteTo.getNombre(), 255, "nombre");
        checkLength(clienteTo.getEmail(), 60, "email");
        checkEmptyString(clienteTo.getTelefono(), "telefono");
        checkLength(clienteTo.getTelefono(), 20, "telefono");

        if (clienteTo.getBeneficio().isEmpty()) {
            throw new ParametroEntradaException("Debe seleccionar un beneficio");
        }
    }
}
